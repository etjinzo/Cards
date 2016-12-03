import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * Write a description of class Interface here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Interface extends JPanel
{
    public Interface() {
        DeckManipulator deck = new DeckManipulator();

        this.setPreferredSize(new Dimension(200, 300));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        final JLabel deckLab = new JLabel("No Deck Available");
        deckLab.setBorder(BorderFactory.createEtchedBorder());
        this.add(deckLab);

        final JLabel currCardLab = new JLabel("No Current Card Available");
        currCardLab.setBorder(BorderFactory.createEtchedBorder());
        this.add(currCardLab);

        JButton createButt = new JButton("Create a Deck");
        createButt.setToolTipText("Creates a deck of 52 cards");
        this.add(createButt);

        class CreateDeckActionListener implements ActionListener {

            public void actionPerformed(ActionEvent ae) {
                deck.createDeck();
                deckLab.setText("Deck Available");
                invalidate();
                validate();
                repaint();
                currCardLab.setText("No Current Card Available");
            }
        }
        createButt.addActionListener(new CreateDeckActionListener());

        JButton pickTopButt = new JButton("Pick Card from the Top");
        pickTopButt.setToolTipText("Picks a card from the top of the deck");
        this.add(pickTopButt);

        JButton pickRandomButt = new JButton("Pick a random Card");
        pickRandomButt.setToolTipText("Picks a Random card");
        this.add(pickRandomButt);

        JButton pickBottomButt = new JButton("Pick a Card from the Bottom");
        pickBottomButt.setToolTipText("Picks a card from the bottom of the deck");
        this.add(pickBottomButt);

        JButton pickPositionButt = new JButton("Pick a Card from a Position");
        pickPositionButt.setToolTipText("Picks a Card from a specified position");
        this.add(pickPositionButt);

        class PickCardActionListener implements ActionListener {
            int n = 0;
            public void actionPerformed(ActionEvent ae) {

                if (deck.checkDeck()){
                    if (ae.getSource() == pickTopButt) {
                        deck.pickCardFromTop(); 
                    } else if (ae.getSource() == pickRandomButt) {
                        deck.pickCardFromRandom();
                    } else if (ae.getSource() == pickBottomButt) {
                        deck.pickCardFromBottom();
                    } 
                    else if (ae.getSource() == pickPositionButt) {
                        boolean done = false;
                        while(!done) {
                            try {
                                n = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter a position (0 - 51): "));
                                if (n < 0 || n > 51) {
                                    JOptionPane.showMessageDialog(null, "Your number is invalid.", "Value Error", JOptionPane.ERROR_MESSAGE);
                                    continue;
                                } else {
                                    deck.pickCardFromPosition(n);
                                }
                                done = true;
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Please enter a number value.", "Input Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    currCardLab.setText("Current card: " + deck.currentCard.getValue() + " of " + deck.currentCard.getSuit());
                }
            }
        }
        pickTopButt.addActionListener(new 
            PickCardActionListener());
        pickRandomButt.addActionListener(new 
            PickCardActionListener());
        pickBottomButt.addActionListener(new 
            PickCardActionListener());
        pickPositionButt.addActionListener(new 
            PickCardActionListener());

        JButton shuffleButt = new JButton("Shuffle Deck");
        createButt.setToolTipText("Shuffles the deck");
        this.add(shuffleButt);

        class ShuffleActionListener implements ActionListener {

            public void actionPerformed(ActionEvent ae) {
                if (deck.checkDeck()){
                    deck.shuffleDeck(); 
                }
            }
        }
        shuffleButt.addActionListener(new ShuffleActionListener());

        JButton clearButt = new JButton("Clear Deck");
        createButt.setToolTipText("Discard current deck");
        this.add(clearButt);

        class ClearActionListener implements ActionListener {

            public void actionPerformed(ActionEvent ae) {
                if (deck.checkDeck()) {
                    deck.clearDeck();
                    deckLab.setText("No Deck Available");
                    currCardLab.setText("No Current Card Available");
                }
            }
        }
        clearButt.addActionListener(new ClearActionListener());

    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Cards Master");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(new FlowLayout());

        f.add(new Interface());

        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
