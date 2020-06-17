import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Notepad extends JFrame implements ActionListener {

    MenuBar mbar;
    Menu file, edit, format, font, font1, font2;
    MenuItem item1, item2, item3, item4;
    MenuItem item5, item6, item7, item8, item9, item10;
    MenuItem fname1, fname2, fname3, fname4;
    MenuItem fstyle1, fstyle2, fstyle3, fstyle4;
    MenuItem fsize1, fsize2, fsize3, fsize4;

    JPanel mainpanel;
    TextArea text;
    Font f;

    String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    GregorianCalendar gcalendar;

    String command = " ";
    String str = "";

    String str1 = "", str2 = "", str3 = "", str4 = "", str5 = "", str6 = "", str7 = "", str8 = "", str9 = "", str10 = "";

    int len, len1;
    int i = 0;
    int pos1;

    public Notepad(String str) {
        super(str);

        mainpanel = new JPanel();
        mainpanel = (JPanel) getContentPane();
        mainpanel.setLayout(new FlowLayout());

        mbar = new MenuBar();
        setMenuBar(mbar);
        file = new Menu("File");
        edit = new Menu("Edit");
        format = new Menu("Format");
        font = new Menu("Font");
        font1 = new Menu("Font Style");
        font2 = new Menu("Font Size");

        file.add(item1 = new MenuItem("New"));
        file.add(item2 = new MenuItem("Open"));
        file.add(item3 = new MenuItem("Save As"));
        file.add(item4 = new MenuItem("Exit"));
        mbar.add(file);

        edit.add(item5 = new MenuItem("Cut"));
        edit.add(item6 = new MenuItem("Copy"));
        edit.add(item7 = new MenuItem("Paste"));
        edit.add(item8 = new MenuItem("Delete"));
        edit.add(item9 = new MenuItem("Select All"));
        edit.add(item10 = new MenuItem("Time/Date"));
        mbar.add(edit);

        format.add(font);
        format.add(font1);
        format.add(font2);

        font.add(fname1 = new MenuItem("Courier"));
        font.add(fname2 = new MenuItem("Tahoma"));
        font.add(fname3 = new MenuItem("Symbol"));
        font.add(fname4 = new MenuItem("Sans Serif"));

        font1.add(fstyle1 = new MenuItem("Regular"));
        font1.add(fstyle2 = new MenuItem("Bold"));
        font1.add(fstyle3 = new MenuItem("Italic"));
        font1.add(fstyle4 = new MenuItem("Bold Italic"));

        font2.add(fsize1 = new MenuItem("12"));
        font2.add(fsize2 = new MenuItem("14"));
        font2.add(fsize3 = new MenuItem("18"));
        font2.add(fsize4 = new MenuItem("20"));

        mbar.add(format);

        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);
        item4.addActionListener(this);
        item5.addActionListener(this);
        item6.addActionListener(this);
        item7.addActionListener(this);
        item8.addActionListener(this);
        item9.addActionListener(this);
        item10.addActionListener(this);
        fname1.addActionListener(this);
        fname2.addActionListener(this);
        fname3.addActionListener(this);
        fname4.addActionListener(this);
        fstyle1.addActionListener(this);
        fstyle2.addActionListener(this);
        fstyle3.addActionListener(this);
        fstyle4.addActionListener(this);
        fsize1.addActionListener(this);
        fsize2.addActionListener(this);
        fsize3.addActionListener(this);
        fsize4.addActionListener(this);

        text = new TextArea(23, 44);
        mainpanel.add(text);

        f = new Font("Courier", Font.PLAIN, 15);
        text.setFont(f);

    }

    public void actionPerformed(ActionEvent ae) {
        command = (String) ae.getActionCommand();

        if (command.equals("New")) {
            dispose();
            Notepad note1 = new Notepad("New Notepad Simulation");
            note1.setSize(520, 500);
            note1.setVisible(true);
        }
        try {
            if (command.equals("Open")) {
                str4 = " ";
                FileDialog dialog = new FileDialog(this, "Open");
                dialog.setVisible(true);

                str1 = dialog.getDirectory();
                str2 = dialog.getFile();
                str3 = str1 + str2;

                File f = new File(str3);
                FileInputStream fobj = new FileInputStream(f);
                len = (int) f.length();
                for (int j = 0; j < len; j++) {
                    char str5 = (char) fobj.read();
                    str4 = str4 + str5;
                }

                text.setText(str4);
            }
        } catch (Exception e) {
            System.out.print(e);
        }

        try {
            if (command.equals("Save As")) {
                FileDialog dialog1 = new FileDialog(this, "Save As", FileDialog.SAVE);
                dialog1.setVisible(true);

                str6 = dialog1.getDirectory();
                str7 = dialog1.getFile();
                str8 = str6 + str7;

                str9 = text.getText();
                len1 = str9.length();
                byte buf[] = str9.getBytes();

                File f1 = new File(str8);
                FileOutputStream fout = new FileOutputStream(f1);

                for (int k = 0; k < len1; k++) {
                    fout.write(buf[k]);
                }
                fout.close();
            }
            this.setTitle(str8);
        } catch (Exception e) {
            System.out.print(e);
        }

        if (command.equals("Exit")) {
            System.exit(0);
        }

        if (command.equals("Cut")) {
            str = text.getSelectedText();
            i = text.getText().indexOf(str);
            text.replaceRange("", i, i + str.length());
        }

        if (command.equals("Copy")) {
            str = text.getSelectedText();
        }
        if (command.equals("Paste")) {
            pos1 = text.getCaretPosition();
            text.insert(str, pos1);
        }
        if (command.equals("Delete")) {
            String msg = text.getSelectedText();
            i = text.getText().indexOf(msg);
            text.replaceRange("", i, i + msg.length());
        }
        if (command.equals("Select All")) {
            String strText = text.getText();
            int strLen = strText.length();
            text.select(0, strLen);
        }
        if (command.equals("Time/Date")) {
            gcalendar = new GregorianCalendar();
            String h = String.valueOf(gcalendar.get(Calendar.HOUR));
            String m = String.valueOf(gcalendar.get(Calendar.MINUTE));
            String s = String.valueOf(gcalendar.get(Calendar.SECOND));
            String date = String.valueOf(gcalendar.get(Calendar.DATE));
            String mon = String.valueOf(gcalendar.get(Calendar.MONTH));
            String year = String.valueOf(gcalendar.get(Calendar.YEAR));
            String mydate = "Time Is:" + " - " + h + ":" + m + ":" + s + "Date Is:" + " - " + date + " " + mon + " " + year;

            int loc = text.getCaretPosition();
            text.insert(mydate, loc);
        }

        if (command.equals("Courier")) {
            String fontname = f.getName();
            String fontFamily = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();
            f = new Font("Courier", fontStyle, fontSize);
            text.setFont(f);
        }
        if (command.equals("Tahoma")) {
            String fontname = f.getName();
            String fontFamily = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();
            f = new Font("Tahoma", fontStyle, fontSize);
            text.setFont(f);
        }
        if (command.equals("Symbol")) {
            String fontname = f.getName();
            String fontFamily = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();
            f = new Font("Symbol", fontStyle, fontSize);
            text.setFont(f);
        }
        if (command.equals("Sans Serif")) {
            String fontname = f.getName();
            String fontFamily = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();
            f = new Font("Sans Serif", fontStyle, fontSize);
            text.setFont(f);
        }
        if (command.equals("Regular")) {
            String fontname = f.getName();
            String fontFamily = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();
            f = new Font(fontname, Font.PLAIN, fontSize);
            text.setFont(f);
        }
        if (command.equals("Bold")) {
            String fontname = f.getName();
            String fontFamily = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();
            f = new Font(fontname, Font.BOLD, fontSize);
            text.setFont(f);
        }
        if (command.equals("Italic")) {
            String fontname = f.getName();
            String fontFamily = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();
            f = new Font(fontname, Font.ITALIC, fontSize);
            text.setFont(f);
        }
        if (command.equals("Bold Italic")) {
            String fontname = f.getName();
            String fontFamily = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();
            f = new Font(fontname, Font.BOLD | Font.ITALIC, fontSize);
            text.setFont(f);
        }

        if (command.equals("12")) {
            String fontname = f.getName();
            String fontFamily = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();
            f = new Font(fontname, fontStyle, 12);
            text.setFont(f);
        }
        if (command.equals("14")) {
            String fontname = f.getName();
            String fontFamily = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();
            f = new Font(fontname, fontStyle, 14);
            text.setFont(f);
        }
        if (command.equals("18")) {
            String fontname = f.getName();
            String fontFamily = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();
            f = new Font(fontname, fontStyle, 18);
            text.setFont(f);
        }
        if (command.equals("20")) {
            String fontname = f.getName();
            String fontFamily = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();
            f = new Font(fontname, fontStyle, 20);
            text.setFont(f);
        }

    }

    public static void main(String args[]) {
        Notepad note1 = new Notepad("NotePad Simulation");
        note1.setSize(520, 500);
        note1.setVisible(true);
    }
}
