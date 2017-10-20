package com.example.nsohoni.fragmentlifecycles;

import java.util.ArrayList;

/**
 * Created by nsohoni on 15/09/17.
 */

public class Test {

    class Item {
        boolean isSpace = false;
        String  str;
    }

    class LineItems {
        ArrayList<Item> line = new ArrayList<>();
        ArrayList<Integer> spacesIndexes = new ArrayList<>();
        public void addWord(Item item) {
            line.add(item);
            if(item.isSpace) {
                spacesIndexes.add(line.size()-1);
            }
        }

        public void processLastLineSpaces(int spaceCount) {
            //add the spaces to the end
            String spaces = new String();
            while(spaceCount > 0) {
                spaces += " ";
                --spaceCount;
            }

            Item i = new Item();
            i.isSpace = true;
            i.str = spaces;
            line.add(i);
        }

        public void processEOLSpaces(int spaceCount) {
            int spaceItems = spacesIndexes.size();
            //always remove space if its the last item
            Item lastItem = line.get(line.size()-1);
            if(lastItem.isSpace) {
                line.remove(line.size()-1);
                spacesIndexes.remove(spaceItems - 1);
                --spaceItems;
                ++spaceCount;
            }

            if(spaceItems > 0) {
                int q = spaceCount / spaceItems;
                int r = spaceCount % spaceItems;

                while (q > 0) {
                    for (int i = 0; i < spaceItems; i++) {
                        Item spaceItem = line.get(spacesIndexes.get(i));
                        spaceItem.str = spaceItem.str + " ";
                    }
                    --q;
                }

                while (r > 0) {
                    for (int i = 0; i < spaceItems; i++) {
                        if(r > 0) {
                            Item spaceItem = line.get(spacesIndexes.get(i));
                            spaceItem.str = spaceItem.str + " ";
                            --r;
                        }
                    }
                }
            } else {
                //add the spaces to the end
                String spaces = new String();
                while(spaceCount > 0) {
                    spaces += " ";
                    --spaceCount;
                }

                Item i = new Item();
                i.isSpace = true;
                i.str = spaces;
                line.add(i);
            }
        }

        public void processLine() {
            String sentence = new String();
            for(int i=0; i< line.size(); i++ ) {
                sentence += line.get(i).str;
            }
            solution.add(sentence);
        }
    }

    String [] words = {"This",
            "is",
            "an",
            "example",
            "of",
            "text",
            "justification."};
    int l = 16;


    ArrayList<String> solution = new ArrayList<>();

    public void solve() {
        int wordIndex = 0;
        int remainder = l;
        LineItems lineItems = new LineItems();
        while (remainder > 0 && wordIndex < words.length) {
            String word = words[wordIndex];
            if (word.length() == remainder) {
                Item i = new Item();
                i.str = new String(word);
                lineItems.addWord(i);
                lineItems.processLine();
                lineItems = new LineItems();
                wordIndex++;
                remainder = l;
                continue;
            } else if(word.length() < remainder){

                Item i = new Item();
                i.str = new String(word);
                lineItems.addWord(i);

                if(word.length() +1 < remainder) {
                    Item space = new Item();
                    space.isSpace = true;
                    space.str = " ";
                    lineItems.addWord(space);
                    remainder -= (word.length() + 1);
                } else {
                    //no space left after adding word
                    remainder -= (word.length());
                }
                wordIndex++;
            } else {
                //process line
                lineItems.processEOLSpaces(remainder);
                lineItems.processLine();
                lineItems = new LineItems();
                remainder = l;
            }
        }

        if(remainder < l) {
            lineItems.processLastLineSpaces(remainder);
            lineItems.processLine();
        }
    }


}
