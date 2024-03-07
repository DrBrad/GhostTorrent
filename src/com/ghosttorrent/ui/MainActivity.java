package com.ghosttorrent.ui;

import com.ghosttorrent.torrent.Torrent;
import com.ghosttorrent.ui.utils.inter.Activity;
import com.ghosttorrent.ui.utils.Intent;
import com.ghosttorrent.ui.utils.layouts.RelativeConstraints;
import com.ghosttorrent.ui.utils.layouts.RelativeLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Intent intent){
        getRoot().setLayout(new BoxLayout(getRoot(), BoxLayout.Y_AXIS));
        getRoot().add(createStatusBar());
        getRoot().add(createFilterBar());

        getRoot().add(createList());
    }

    private JComponent createStatusBar(){
        JPanel pane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pane.setBackground(R.colors.get("background-secondary"));
        pane.add(new JButton("Open"));
        pane.add(new JButton("Run"));
        //pane.add(new JButton("Pause")); //WE COULD COMBINE THIS WITH RUN...
        pane.add(new JButton("Remove"));
        pane.add(new JButton("Properties"));
        return pane;
    }

    private JComponent createFilterBar(){
        JPanel pane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pane.setBackground(R.colors.get("background-secondary"));
        JLabel label = new JLabel("Show");
        label.setForeground(R.colors.get("text-primary"));
        pane.add(label);
        //MENU (TYPE OF TORRENT)
        //MENU (TRACKER)
        //SEARCH BY NAME
        return pane;
    }

    private JComponent createList(){
        JPanel pane = new JPanel();
        pane.setLayout(new BorderLayout());

        JList list = new JList();

        DefaultListModel model = new DefaultListModel();

        for(int i = 0; i < 10; i++){
            Torrent torrent = new Torrent();
            torrent.setTitle("Breaking Bad");
            torrent.setObtained(10000);
            torrent.setTotal(30000);
            torrent.setPeers(20);
            model.addElement(torrent);
        }

        list.setCellRenderer(new CellRenderer());
        list.setModel(model);

        //sideList.setLayout(new RelativeLayout());
        //list.setBackground(Color.decode("#d6d6d6"));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);

        //DefaultListModel sideModel = new DefaultListModel();
        //addSideModel(sideModel);
        //list.setCellRenderer(new SideCellRenderer());
        //list.setModel(sideModel);
        JScrollPane scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new MatteBorder(1, 0, 0, 0, R.colors.get("background-shimmer")));

        /*
        for(int i = 0; i < 100; i++){
            list.add("hello "+i);
        }
        */

        pane.add(scrollPane);

        list.addMouseMotionListener(new MouseMotionListener(){
            @Override
            public void mouseDragged(MouseEvent e){
            }

            @Override
            public void mouseMoved(MouseEvent e){
                if(list.indexToLocation(model.size()-1).getY()+60 > e.getY()){
                    list.setCursor(new Cursor(Cursor.HAND_CURSOR));

                }else{
                    list.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });

        return pane;
    }

    public String humanReadableByteCountBin(long bytes){
        long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        if(absB < 1024){
            return bytes + " B";
        }
        long value = absB;
        CharacterIterator ci = new StringCharacterIterator("KMGTPE");
        for(int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10){
            value >>= 10;
            ci.next();
        }
        value *= Long.signum(bytes);
        return String.format("%.1f %cB", value / 1024.0, ci.current());
    }

    public class CellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean selected, boolean expanded){
            Torrent torrent = (Torrent) value;

            JPanel pane = new JPanel();
            pane.setLayout(new RelativeLayout());//new FlowLayout(FlowLayout.LEFT));
            pane.setBorder(new MatteBorder(0, 0, 1, 0, R.colors.get("background-shimmer")));
            //pane.setBackground(Color.red);
            pane.setPreferredSize(new Dimension(480, 80));
            //pane.setOpaque(true);



            //IMAGE - 60 x 60?
            JLabel icon = new JLabel(R.images.get("ic_folder"));
            pane.add(icon, new RelativeConstraints().setWidth(48).setHeight(48).centerVertically().setMargins(new Insets(10, 10, 10, 10)));

            JPanel content = new JPanel();
            //content.setBackground(Color.black);
            content.setOpaque(true);
            content.setLayout(new GridBagLayout());

            GridBagConstraints constraints = new GridBagConstraints();
            //constraints.insets = new Insets(0, 80, 0, 0);
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.weightx = 1;
            constraints.weighty = 1;
            constraints.gridx = 0;
            constraints.gridy = 0;

            JLabel title = new JLabel(torrent.getTitle());
            title.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
            content.add(title, constraints);

            JLabel description = new JLabel(humanReadableByteCountBin(torrent.getObtained())+" of "+humanReadableByteCountBin(torrent.getTotal())+" (0.0%)");
            description.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            constraints.gridy = 1;
            content.add(description, constraints);

            JProgressBar progress = new JProgressBar();
            progress.setBorder(new EmptyBorder(0, 0, 0, 0));
            progress.setForeground(R.colors.get("primary"));
            progress.setBackground(R.colors.get("background-shimmer"));
            progress.setValue(10);
            //progress.setMaximum(100);
            constraints.gridy = 2;
            content.add(progress, constraints);

            //PROGRESS BAR
            JLabel details = new JLabel("Downloading from "+torrent.getPeers()+" peers");
            details.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            constraints.gridy = 3;
            content.add(details, constraints);


            if(selected){
                title.setForeground(R.colors.get("background"));
                description.setForeground(R.colors.get("background"));
                details.setForeground(R.colors.get("background"));

                pane.setBackground(R.colors.get("primary"));
                content.setBackground(R.colors.get("primary"));

            }else{
                title.setForeground(R.colors.get("text-primary"));
                description.setForeground(R.colors.get("text-secondary"));
                details.setForeground(R.colors.get("text-secondary"));

                pane.setBackground(R.colors.get("background"));
                content.setBackground(R.colors.get("background"));
            }

            pane.add(content, new RelativeConstraints().setHeight(60).centerVertically().toRightOf(icon).alignParentRight().setMarginLeft(10).setMarginRight(10));


            return pane;
        }
    }
}
