// Jingnan Wang (40282296), Mugisha Samuel Rugomwa (40265564)
// COMP249
// Assignment #3
// Due Date: March 27 2024

/**
 * This class reads a input file of type .txt containing the names of different topics and associated words, it also allows the user to perform various operations on the topics and words such as adding, removing, modifying, searching, and saving to a file.
 * In this assignment we have implemented a doubly linked list of Vocab objects, each Vocab object contains a topic and a singly linked list of words.
 * @author Jingnan Wang
 * @author Mugisha Samuel Rugomwa
 * @version 1.0
 **/
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.w3c.dom.Node;

import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class Driver {
    /**
     * The doubly linked list of Vocab objects
     */
    static DoublyLinkedList vocab_list = new DoublyLinkedList();

    /*
     * The array list of topics
     */
    static ArrayList<String> topics = new ArrayList<>();
    /*
     * The array list of all words from vocab objects
     */
    static ArrayList<String> allWords = new ArrayList<>();

    /**
     * The main menu of the program
     * @return
     */
    public static void mainMenu() {
        System.out.println("""
                -----------------------------
                Vocabulary Control Center
                -----------------------------""");

        System.out.println("1 browse a topic");
        System.out.println("2 insert a new topic before another one");
        System.out.println("3 insert a new topic after another one");
        System.out.println("4 remove a topic");
        System.out.println("5 modify a topic");
        System.out.println("6 search topics for a word");
        System.out.println("7 load from a file");
        System.out.println("8 show all words starting with a given letter");
        System.out.println("9 save to file");
        System.out.println("0 exit");

        System.out.println("-------------------------------" + "\nEnter your choice");
    }

    /*
     * The modify topic menu
     * @return
     */

    public static void modifyTopicMenu() {
        System.out.println("""
                -----------------------------
                Modify Topic Menu
                -----------------------------""");

        System.out.println("a add a word ");
        System.out.println("r remove a word");
        System.out.println("c change a word");
        System.out.println("0 exit");

        System.out.println("-------------------------------" + "\nEnter your choice");

    }

    /**
     * The main method of the program
     * 
     * @param args
     * @return
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // create a doubly linked list of Vocab objects
        // implement menu

        int selection;
        do {
            mainMenu();
            selection = sc.nextInt();

            switch (selection) {

                case 0:
                    System.out.println("Goodbye! See you later!");
                    System.exit(0);
                    break;

                case 1:
                    vocab_list.display();
                    int chosenTopic = sc.nextInt();
                    if (chosenTopic == 0) {
                        break;
                    } else if (chosenTopic > topics.size()) {
                        System.out.println("Topic not found");
                    } else {
                        System.out.println("Words in this topic are: ");
                        vocab_list.get(chosenTopic - 1).getWords().display();

                    }

                    break;

                case 2:

                    try {

                        // insert a new topic before another one
                        vocab_list.display();
                        int beforeTopic = sc.nextInt();
                        sc.nextLine();
                        Vocab beforeVocab = vocab_list.get(beforeTopic - 1);

                        System.out.println("Enter a new topic name:");
                        String newTopic = sc.nextLine();
                        Vocab newVocab = new Vocab(newTopic);

                        SinglyLinkedList tempWordsList = new SinglyLinkedList();

                        // add words to the newTopic
                        System.out.println("Enter a new word for the new topic - to quit press Enter");
                        String addedWord = sc.nextLine();

                        while (!addedWord.equals("")) {
                            tempWordsList.addAtTail(addedWord);
                            addedWord = sc.nextLine();
                        }
                        newVocab.setWords(tempWordsList);

                        vocab_list.addBefore(beforeVocab, newVocab);

                    } catch (Exception e) {
                        System.out.println("Topic not found");
                    }
                    break;

                case 3:
                    // insert a new topic after another one
                    try {
                        vocab_list.display();
                        int afterTopic = sc.nextInt();
                        sc.nextLine();
                        Vocab afterVocab = vocab_list.get(afterTopic - 1);

                        System.out.println("Enter a new topic name:");
                        String newTopic = sc.nextLine();
                        Vocab newVocab = new Vocab(newTopic);

                        SinglyLinkedList tempWordsList = new SinglyLinkedList();

                        // add words to the newTopic
                        System.out.println("Enter a new word for the new topic - to quit press Enter");
                        String addedWord = sc.nextLine();

                        while (!addedWord.equals("")) {
                            tempWordsList.addAtTail(addedWord);
                            addedWord = sc.nextLine();
                        }
                        newVocab.setWords(tempWordsList);

                        vocab_list.addAfter(afterVocab, newVocab);

                    } catch (Exception e) {
                        System.out.println("Topic not found");
                    }
                    break;

                case 4:
                    // remove a topic from the list of available topics

                    vocab_list.display();
                    int remTopic = sc.nextInt();
                    sc.nextLine();

                    // remove the topic from the list of topics
                    Vocab remVocab = vocab_list.get(remTopic - 1);
                    vocab_list.removeTopic(remVocab);

                    break;

                case 5:
                    // modify a topic
                    vocab_list.display();
                    int selectedTopicToModify = sc.nextInt();
                    if (selectedTopicToModify == 0) {
                        break;
                    } else if (selectedTopicToModify > topics.size()) {
                        System.out.println("Topic not found");
                    }
                    modifyTopicMenu();
                    String modifyTopic = sc.next();
                    System.out.println("Type a word and press Enter, or press Enter to end input");
                    String addedWord = sc.next();

                    if (vocab_list.get(selectedTopicToModify - 1).getWords().equals(addedWord)) {
                        System.out.println("Word already exists");
                    } else {
                        if (modifyTopic.equals("a") || modifyTopic.equals("A")) {

                            vocab_list.get(selectedTopicToModify - 1).getWords().addAtTail(addedWord);
                            allWords.add(addedWord);

                        } else if (modifyTopic.equals("r") || modifyTopic.equals("R")) {
                            vocab_list.get(selectedTopicToModify - 1).getWords().removeWord(addedWord);
                            allWords.remove(addedWord);

                        } else if (modifyTopic.equals("c") || modifyTopic.equals("C")) {

                        } else if (modifyTopic.equals(0)) {
                            break;
                        }

                    }
                    break;

                case 6:
                    // search topics for a word
                    vocab_list.display();
                    int selectedTopic = sc.nextInt();
                    sc.nextLine();

                    if (selectedTopic == 0) {
                        break;
                    } else if (selectedTopic > topics.size()) {
                        System.out.println("Topic not found");
                    } else {
                        System.out.println("Enter a word to search for: ");
                        String wordToSearch = sc.next();
                        if (vocab_list.get(selectedTopic - 1).getWords().equals(wordToSearch)) {
                            System.out.println("Word found in topic: "
                                    + vocab_list.get(selectedTopic - 1).getTopic());
                        } else {
                            System.out.println("Word not found");
                        }
                    }
                    break;

                case 7:

                    // load from a file
                    Scanner keyboard = new Scanner(System.in);
                    System.out.println("Enter the file name: ");
                    String fileName = keyboard.nextLine();
                    Scanner fReader = null;
                    try {
                        // read A3_input file
                        fReader = new Scanner(new FileInputStream(fileName + ".txt"));

                        String line;

                        // boolean looping = true;
                        while (fReader.hasNextLine()) {
                            // split the line by # and store the vocab objects in a doubly linked list
                            line = fReader.nextLine();
                            if (!line.isEmpty() && line.charAt(0) == '#') {
                                String topic = line.substring(1);
                                // creates a new Vocab object with the topic as its argument
                                Vocab vocab = new Vocab(topic);
                                // add the vocab object to the doubly linked list
                                vocab_list.addAtTail(vocab);
                                topics.add(topic);

                                // add the words to the singly linked list

                            } else if (!line.isEmpty() && line.charAt(0) != '#') {
                                // add words to the the last vocab object in the doubly linked list
                                vocab_list.get(vocab_list.size() - 1).getWords().addAtTail(line);
                                // add the words to the allWords array list
                                allWords.add(line);
                            }

                        }

                        System.out.println("Done loading" + "\n");

                    } catch (FileNotFoundException fnfe) {
                        System.out.println("File not found: " + fnfe.getMessage());
                        System.exit(1);

                    } finally {
                        if (fReader != null) {
                            try {
                                fReader.close();
                            } catch (Exception e) {
                                System.out.println("Error closing file: " + e.getMessage());
                            }
                        }
                    }

                    break;

                case 8:
                    // display all words starting with a given letter

                    System.out.println("Enter a letter: ");
                    char c = sc.next().charAt(0);
                    Collections.sort(allWords);
                    for (String s : allWords) {
                        if (s.charAt(0) == c) {
                            System.out.println(s);
                        }
                    }

                    break;

                case 9:
                    // save to file
                    
                        
                        vocab_list.saveOutputFile();
                        
                        
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (selection != 0);

    }

}
