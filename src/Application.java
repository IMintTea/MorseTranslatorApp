import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import javax.sound.sampled.LineUnavailableException;

public class Application extends javax.swing.JFrame{
    private JTextArea textTf;
    private JTextArea translatedTextTf;
    private JButton translateBtn;
    private JPanel applicationPanel;
    private JLabel titleLb;
    private JButton copyBtn;
    private JLabel arrowLb;
    private JScrollPane textSp;
    private JScrollPane translatedTextSp;
    private JButton soundBtn;
    private JButton clearBtn;

    private Sound sound;
    Toolkit tk = Toolkit.getDefaultToolkit();
    int xSize =((int) tk.getScreenSize().getWidth());
    int ySize =((int) tk.getScreenSize().getHeight());

    Application(){

        sound = new Sound();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize((new Dimension(1125,510)));
        this.setResizable(false);
        this.setVisible(true);
        this.setLocation((xSize/2)-(this.getWidth()/2),(ySize/2)-(this.getHeight()/2));

        this.add(applicationPanel);
        applicationPanel.setVisible(true);
        applicationPanel.setBackground(new Color(0x121212));

        //<editor-fold defaultstate="collapsed" desc="Title label">
        titleLb.setText("Morse Code Translator");
        titleLb.setForeground(new Color(0xe0e0e0));
        titleLb.setFont(new Font("Poppins",Font.BOLD,50));
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Text Scroll panes">
        textSp.setMinimumSize(new Dimension(375,375));
        textSp.setBackground(new Color(0x1e1e1e));
        textSp.setForeground(new Color(0xe0e0e0));
        textSp.setBorder(null);

        translatedTextSp.setMinimumSize(new Dimension(375,375));
        translatedTextSp.setBackground(new Color(0x1e1e1e));
        translatedTextSp.setForeground(new Color(0xe0e0e0));
        translatedTextSp.setBorder(null);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Text fields">
        textTf.setMinimumSize(new Dimension(375,375));
        textTf.setFont(new Font("Poppins",Font.PLAIN,20));
        textTf.setBackground(new Color(0x1e1e1e));
        textTf.setForeground(new Color(0xe0e0e0));
        textTf.setBorder(new LineBorder(new Color(0x323232)));
        textTf.setBorder(new UiGraphics(10));
        textTf.setLineWrap(true);
        textTf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                textTf.setBackground(new Color(0x1a1a1a));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                textTf.setBackground(new Color(0x1e1e1e));
            }
        });

        translatedTextTf.setMinimumSize(new Dimension(375,375));
        translatedTextTf.setFont(new Font("Poppins",Font.PLAIN,20));
        translatedTextTf.setBackground(new Color(0x1e1e1e));
        translatedTextTf.setForeground(new Color(0xe0e0e0));
        translatedTextTf.setBorder(new LineBorder(new Color(0x323232)));
        translatedTextTf.setBorder(new UiGraphics(10));
        translatedTextTf.setLineWrap(true);
        translatedTextTf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                translatedTextTf.setBackground(new Color(0x1a1a1a));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                translatedTextTf.setBackground(new Color(0x1e1e1e));
            }
        });
        //</editor-fold>

        translateBtn.setText("Translate");
        translateBtn.setMinimumSize(new Dimension(75,25));
        translateBtn.setFont(new Font("Poppins",Font.PLAIN,20));
        translateBtn.setBackground(new Color(0x1e1e1e));
        translateBtn.setForeground(new Color(0xe0e0e0));
        translateBtn.setBorder(new LineBorder(new Color(0x323232)));
        translateBtn.setFocusPainted(false);
        translateBtn.setBorder(new UiGraphics(10));
        translateBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                translateBtn.setBackground(new Color(0x1a1a1a));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                translateBtn.setBackground(new Color(0x1e1e1e));
            }
        });

        translateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sentence = textTf.getText();

                ArrayList<String> sentenceToArray = new ArrayList<>();
                sentence = sentence.toUpperCase();
                for (int s = 0; s < sentence.length(); s++) {
                    sentenceToArray.add(String.valueOf(sentence.charAt(s)));
                }

                char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
                String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

                StringBuilder translatedAnswer = new StringBuilder();
                for (int i = 0; i < sentenceToArray.size(); i++) {
                    for (int j = 0; j < letters.length; j++) {
                        if (String.valueOf(letters[j]).equals(sentenceToArray.get(i))){
                            String.valueOf(translatedAnswer.append(morse[j] + " "));
                        }else if (sentenceToArray.get(i).equals(" ")){
                            String.valueOf(translatedAnswer.append("/ "));
                            break;
                        }
                    }
                }
                translatedTextTf.setText(String.valueOf(translatedAnswer));
                System.out.println(translatedAnswer);
            }
        });

        //<editor-fold defaultstate="collapsed" desc="Arrow label">
        arrowLb.setText(">>>>");
        arrowLb.setFont(new Font("Poppins",Font.PLAIN,20));
        arrowLb.setForeground(new Color(0xe0e0e0));
        //</editor-fold>

        copyBtn.setText("Copy");
        copyBtn.setMinimumSize(new Dimension(75,25));
        copyBtn.setFont(new Font("Poppins",Font.PLAIN,20));
        copyBtn.setBackground(new Color(0x1e1e1e));
        copyBtn.setForeground(new Color(0xe0e0e0));
        copyBtn.setBorder(new LineBorder(new Color(0x323232)));
        copyBtn.setFocusPainted(false);
        copyBtn.setBorder(new UiGraphics(10));
        copyBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                copyBtn.setBackground(new Color(0x1a1a1a));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                copyBtn.setBackground(new Color(0x1e1e1e));
            }
        });
        copyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textToCopy = translatedTextTf.getText();

                StringSelection stringSelection = new StringSelection(textToCopy);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        });

        clearBtn.setText("Clear");
        clearBtn.setMinimumSize(new Dimension(75,25));
        clearBtn.setFont(new Font("Poppins",Font.PLAIN,20));
        clearBtn.setBackground(new Color(0x1e1e1e));
        clearBtn.setForeground(new Color(0xe0e0e0));
        clearBtn.setBorder(new LineBorder(new Color(0x323232)));
        clearBtn.setFocusPainted(false);
        clearBtn.setBorder(new UiGraphics(10));
        clearBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clearBtn.setBackground(new Color(0x1a1a1a));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                clearBtn.setBackground(new Color(0x1e1e1e));
            }
        });
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textTf.setText("");
                translatedTextTf.setText("");
            }
        });

        soundBtn.setText("Play Sound");
        soundBtn.setMinimumSize(new Dimension(75,25));
        soundBtn.setFont(new Font("Poppins",Font.PLAIN,20));
        soundBtn.setBackground(new Color(0x1e1e1e));
        soundBtn.setForeground(new Color(0xe0e0e0));
        soundBtn.setBorder(new LineBorder(new Color(0x323232)));
        soundBtn.setFocusPainted(false);
        soundBtn.setBorder(new UiGraphics(10));
        soundBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                soundBtn.setBackground(new Color(0x1a1a1a));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                soundBtn.setBackground(new Color(0x1e1e1e));
            }
        });
        soundBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                soundBtn.setEnabled(false);

                Thread playMorseCodeThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String[] morseCodeMessage = translatedTextTf.getText().split(" ");
                        if (!morseCodeMessage.equals(null)){
                            try{
                                Sound.playSound(morseCodeMessage);
                            }catch(LineUnavailableException lineUnavailableException){
                                lineUnavailableException.printStackTrace();
                            }catch(InterruptedException interruptedException){
                                interruptedException.printStackTrace();
                            }finally{
                                soundBtn.setEnabled(true);
                            }
                        }
                    }
                });
                playMorseCodeThread.start();
            }
        });

    }

}
