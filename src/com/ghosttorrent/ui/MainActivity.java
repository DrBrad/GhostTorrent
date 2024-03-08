package com.ghosttorrent.ui;

import com.ghosttorrent.torrent.Torrent;
import com.ghosttorrent.ui.utils.Bundle;
import com.ghosttorrent.ui.utils.inter.Activity;
import com.ghosttorrent.ui.res.views.Panel;
import com.ghosttorrent.ui.utils.layouts.RelativeConstraints;
import com.ghosttorrent.ui.utils.layouts.RelativeLayout;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.main_activity);

        getRoot().setLayout(new BoxLayout(getRoot(), BoxLayout.Y_AXIS));

        ((FlowLayout) findViewById(R.id.statusbar).getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout) findViewById(R.id.filterbar).getLayout()).setAlignment(FlowLayout.LEFT);

        JButton[] buttons = new JButton[]{
                (JButton) findViewById(R.id.statusbar_play),
                (JButton) findViewById(R.id.statusbar_remove),
                (JButton) findViewById(R.id.statusbar_settings)
        };

        for(JButton button : buttons){
            button.setPreferredSize(new Dimension(48, 48));
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setBorder(null);
        }

        findViewById(R.id.content).setBackground(UIManager.getColor("Panel.background").darker());
        findViewById(R.id.content).add(createList());
    }

    private JComponent createList(){
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
        //scrollPane.setBorder(new MatteBorder(1, 0, 0, 0, findColorById(R.color.background_shimmer)));
        scrollPane.getViewport().putClientProperty("EnableWindowBlit", Boolean.TRUE);
        scrollPane.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
        scrollPane.setBackground(UIManager.getColor("Panel.background").darker());

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

        int increment = 16;
        scrollPane.getVerticalScrollBar().setUnitIncrement(increment);
        KeyStroke kUp = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
        KeyStroke kDown = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
        scrollPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(kUp, "actionWhenKeyUp");
        scrollPane.getActionMap().put("actionWhenKeyUp", new AbstractAction("keyUpAction"){

            public void actionPerformed(ActionEvent e){
                final JScrollBar bar = scrollPane.getVerticalScrollBar();
                int currentValue = bar.getValue();
                bar.setValue(currentValue - increment);
            }
        });
        scrollPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(kDown, "actionWhenKeyDown");
        scrollPane.getActionMap().put("actionWhenKeyDown", new AbstractAction("keyDownAction"){

            public void actionPerformed(ActionEvent e){
                final JScrollBar bar = scrollPane.getVerticalScrollBar();
                int currentValue = bar.getValue();
                bar.setValue(currentValue + increment);
            }
        });

        return scrollPane;
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

            Panel pane = (Panel) inflateLayout(R.layout.torrent_item);
            pane.setBorder(new MatteBorder(0, 0, 1, 0, UIManager.getColor("Panel.background")));
            pane.setPreferredSize(new Dimension(480, 120));

            JLabel icon = (JLabel) pane.findViewById(R.id.torrent_item_icon);
            ((RelativeLayout) pane.getLayout()).setConstraints(icon, new RelativeConstraints().setWidth(48).setHeight(48).centerVertically().setMargins(new Insets(10, 10, 10, 10)));

            Panel content = (Panel) pane.findViewById(R.id.torrent_item_content);
            ((RelativeLayout) pane.getLayout()).setConstraints(content, new RelativeConstraints().setHeight(100).centerVertically().toRightOf(icon).alignParentRight().setMarginLeft(10).setMarginRight(10));

            GridBagLayout contentLayout = (GridBagLayout) content.getLayout();

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.weightx = 1;
            constraints.weighty = 1;
            constraints.gridx = 0;
            constraints.gridy = 0;

            JLabel title = (JLabel) content.findViewById(R.id.torrent_item_title);
            title.setText("Breaking Bad");
            contentLayout.setConstraints(title, constraints);

            JLabel description = (JLabel) content.findViewById(R.id.torrent_item_description);
            description.setText(humanReadableByteCountBin(torrent.getObtained())+" of "+humanReadableByteCountBin(torrent.getTotal())+" (0.0%)");
            constraints.gridy = 1;
            constraints.insets = new Insets(5, 0, 5, 0);
            contentLayout.setConstraints(description, constraints);

            JProgressBar progress = (JProgressBar) content.findViewById(R.id.torrent_item_progress);
            progress.setPreferredSize(new Dimension(100, 8));
            progress.setValue(10);
            constraints.gridy = 2;
            contentLayout.setConstraints(progress, constraints);

            JLabel details = (JLabel) content.findViewById(R.id.torrent_item_details);
            details.setText("Downloading from "+torrent.getPeers()+" peers");
            constraints.gridy = 3;
            constraints.insets = new Insets(5, 0, 0, 0);
            contentLayout.setConstraints(details, constraints);

            if(selected){
                title.setForeground(UIManager.getColor("MenuItem.selectionForeground"));
                description.setForeground(UIManager.getColor("MenuItem.selectionForeground"));
                details.setForeground(UIManager.getColor("MenuItem.selectionForeground"));

                //progress.setStringPainted(true);
                //progress.setForeground(Color.RED);//UIManager.getColor("Panel.background").darker());
                //progress.setBackground(Color.BLUE);//UIManager.getColor("MenuItem.selectionBackground").darker());

                pane.setBackground(UIManager.getColor("MenuItem.selectionBackground"));
                content.setBackground(UIManager.getColor("MenuItem.selectionBackground"));

            }else{
                pane.setBackground(UIManager.getColor("Panel.background").darker());
                content.setBackground(UIManager.getColor("Panel.background").darker());
            }


            /*
            if(selected){
                title.setForeground(findColorById(R.color.background));
                description.setForeground(findColorById(R.color.background));
                details.setForeground(findColorById(R.color.background));

                pane.setBackground(findColorById(R.color.secondary));
                content.setBackground(findColorById(R.color.secondary));

            }else{
                title.setForeground(findColorById(R.color.text_primary));
                description.setForeground(findColorById(R.color.text_secondary));
                details.setForeground(findColorById(R.color.text_secondary));

                pane.setBackground(findColorById(R.color.background));
                content.setBackground(findColorById(R.color.background));
            }
            */

            return pane;
        }
    }
}
