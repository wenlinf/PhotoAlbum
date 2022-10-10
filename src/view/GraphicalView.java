package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import model.ISnapshot;
import model.Snapshot;

/**
 * The graphical view of the photo album.
 */
public class GraphicalView extends JFrame implements IPhotoAlbumView, ActionListener {
  private int windowWidth;
  private int windowHeight;
  private JLabel snapshotID;
  private JLabel snapshotDesc;
  private JFrame frame;
  private ShapePainter shapePainter;
  private ISnapshot current;
  private Map<String, ISnapshot> dict;
  private List<Snapshot> snapshots;

  private static final int BOTTOM_COLOR_R = 235;
  private static final int BOTTOM_COLOR_G = 183;
  private static final int BOTTOM_COLOR_B = 52;

  private static final int TOP_COLOR_R = 237;
  private static final int TOP_COLOR_G = 157;
  private static final int TOP_COLOR_B = 174;

  private static final int FRAME_COLOR_R = 63;
  private static final int FRAME_COLOR_G = 138;
  private static final int FRAME_COLOR_B = 242;

  @Override
  public void render(List<Snapshot> snapshots, List<String> args) {
    // if there is no snapshots to display, throw an exception
    if (snapshots.size() == 0) {
      throw new IllegalArgumentException("No snapshots to display");
    }

    // set default view to be the first snapshot
    this.current = snapshots.get(0);
    this.snapshots = snapshots;
    this.dict = new LinkedHashMap<>();
    for (Snapshot snapshot : snapshots) {
      this.dict.put(snapshot.getID(), snapshot);
    }
    // if user does not specify view width and height, set to default
    if (args.size() < 2) {
      windowWidth = 1000;
      windowHeight = 1000;
    } else {
      windowWidth = Integer.parseInt(args.get(0));
      windowHeight = Integer.parseInt(args.get(1));
    }

    // draw The whole panel
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    // create the top panel
    JPanel topPanel = new JPanel(new BorderLayout());
    topPanel.setBackground(new Color(TOP_COLOR_R, TOP_COLOR_G, TOP_COLOR_B));
    this.snapshotID = new JLabel(
            "Snapshot " + (this.snapshots.indexOf(current) + 1) + " - "
            + current.getID());
    this.snapshotDesc = new JLabel(current.getDescription());
    topPanel.add(snapshotID);
    topPanel.add(snapshotDesc, BorderLayout.SOUTH);
    panel.add(topPanel, BorderLayout.NORTH);

    // create the snapshot panel
    ShapePainter snapshot = new ShapePainter(current.getShapes());
    this.shapePainter = snapshot;
    panel.add(snapshot);

    // create the bottom panel with the buttons
    JPanel bottomPanel = new JPanel();
    bottomPanel.setBackground(new Color(BOTTOM_COLOR_R, BOTTOM_COLOR_G, BOTTOM_COLOR_B));
    panel.add(bottomPanel, BorderLayout.SOUTH);

    // create the buttons
    JButton prev = new JButton("<< Prev <<");
    prev.setActionCommand("prev");
    prev.addActionListener(this);
    bottomPanel.add(prev);

    JButton select = new JButton("^^ Select ^^");
    select.setActionCommand("select");
    select.addActionListener(this);
    bottomPanel.add(select);

    JButton next = new JButton(">> Next >>");
    next.setActionCommand("next");
    bottomPanel.add(next);
    next.addActionListener(this);

    JButton quit = new JButton("xx Quit xx");
    quit.setActionCommand("quit");
    quit.addActionListener(this);
    bottomPanel.add(quit);

    JFrame frame = new JFrame();
    frame.setSize(this.windowWidth, this.windowHeight);
    frame.setTitle("CS5004 Wenlin Photo Album");
    frame.setBackground(new Color(FRAME_COLOR_R, FRAME_COLOR_G, FRAME_COLOR_B));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.setVisible(true);
    frame.setResizable(false);
    this.frame = frame;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if ("prev".equals(event.getActionCommand())) {
      int index = this.snapshots.indexOf(this.current);
      // if user is already viewing the first snapshot
      if (index == 0) {
        JOptionPane.showMessageDialog(
                this.frame,
                "Already the first snapshot in the album :)",
                "No more snapshots",
                JOptionPane.PLAIN_MESSAGE);
      } else {
        this.current = this.snapshots.get(index - 1);
      }
    } else if ("select".equals(event.getActionCommand())) {
      // create the dropdown options
      String[] snapshotIDs = new String[this.snapshots.size()];
      for (int i = 0; i < snapshots.size(); i++) {
        snapshotIDs[i] = snapshots.get(i).getID();
      }
      String input = (String) JOptionPane.showInputDialog(
              this.frame,
              "Select a snapshot",
              "Go to snapshot",
              JOptionPane.QUESTION_MESSAGE,
              null,
              snapshotIDs,
              this.current.getID());
      if (input != null) {
        this.current = this.dict.get(input);
      }
    } else if ("next".equals(event.getActionCommand())) {
      int index = this.snapshots.indexOf(this.current);
      // if user is already at the end of the album
      if (index == this.snapshots.size() - 1) {
        JOptionPane.showMessageDialog(
                this.frame,
                "End of album. No more snapshots to show :(",
                "End of album",
                JOptionPane.PLAIN_MESSAGE);
      } else {
        this.current = this.snapshots.get(index + 1);
      }
    } else if ("quit".equals(event.getActionCommand())) {
      System.exit(0);
    }
    // update the view based on user selection
    this.snapshotID.setText(
            "Snapshot " + (this.snapshots.indexOf(current) + 1) + " - "
                    + current.getID());
    this.snapshotDesc.setText(current.getDescription());
    this.shapePainter.setShapes(this.current.getShapes());
    this.shapePainter.repaint();
  }
}
