package com.friendlyblob.islandlife.client.mapeditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sun.org.mozilla.javascript.internal.ast.ForInLoop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.XmlReader;

public class ObjectEditorPanel extends JPanel implements MouseListener, MouseMotionListener {

	private ObjectEditorWindow parent;
	
	private Image activeTexture;
	
	private Point centerPoint;
	
	private ArrayList<Point> points;
		
	/**
	 * Create the panel.
	 */
	public ObjectEditorPanel(ObjectEditorWindow window) {
		this.parent = window;
		
		points = new ArrayList<Point>();
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void save(XmlReader.Element xml) {

		if (parent.setCenterPoint.getModel().isSelected()) {
			String centerPoint = (String) parent.table.getModel().getValueAt(parent.selectedObjectId, 5);
			xml.setAttribute("centerpoint", centerPoint);
		} else if (parent.setCollision.getModel().isSelected()) {
			if (points.size() < 4) {
				JOptionPane.showMessageDialog(this, "You need to select 4 points for a collision.");
				return;
			}
					
			String collisions = (String) parent.table.getModel().getValueAt(parent.selectedObjectId, 2);
			xml.setAttribute("collision", collisions);
		}
		
		PrintWriter out;
		try {
			out = new PrintWriter("data/objects.xml");
			out.write(parent.getXmlRoot().toString());
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		// Draw texture and its outline
		if (activeTexture != null) {
			g2d.drawImage(activeTexture, 0, 0, null);
			
			g2d.setColor(Color.CYAN);
			g2d.drawRect(0, 0, activeTexture.getWidth(null), activeTexture.getHeight(null));
		}
		
		// Draw collision points
		g2d.setColor(Color.GREEN);
		for (int i = 0; i < points.size(); i++) {
			g2d.fillRect(points.get(i).x-2, points.get(i).y-2, 4, 4);
			
			if (i != points.size() - 1) {
				g2d.drawLine(points.get(i).x, points.get(i).y, points.get(i+1).x, points.get(i+1).y);
			} else {
				g2d.drawLine(points.get(i).x, points.get(i).y, points.get(0).x, points.get(0).y);
			}
		}
		//Draw center point
		if (centerPoint != null) {
			g2d.setColor(Color.yellow);
			g2d.fillRect(centerPoint.x-2, centerPoint.y-2, 4, 4);
		}
	}
	
	
	public void loadObject(XmlReader.Element object) {
		try {
			activeTexture = ImageIO.read(new File(object.getAttribute("texture").toString()));
			
			String tmp = object.getAttribute("centerpoint").toString();
			if (tmp != null) {
				if (tmp.matches("\\d+:\\d+")) {
					String[] parts = tmp.split(":");
					
					centerPoint = new Point(new Integer(parts[0]), activeTexture.getHeight(null) -  new Integer(parts[1]));
				}
			}
			
			String collision = object.getAttribute("collision").toString();
			if (collision != null) {
				if (collision.matches("(-?\\d+:-?\\d+;){3}-?\\d+:-?\\d+")) {
					String[] spoints = collision.split(";");
					
					points.clear();
					for (String p : spoints) {
						String[] parts = p.split(":");
						
						Point pt = new Point(centerPoint.x + new Integer(parts[0]), centerPoint.y - new Integer(parts[1])); 
						points.add(pt);
						System.out.println("center point: " + centerPoint.x + " " + centerPoint.y);
						System.out.println("parts: " + parts[0] + " " + parts[1]);
						System.out.println(pt.x + " " + pt.y);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {	
		if (parent.setCollision.getModel().isSelected()) {
			if (points.size() < 4) {
				points.add(new Point(e.getX(), e.getY()));
			}
		} else if (parent.setCenterPoint.getModel().isSelected()) {
			if (centerPoint == null) {
				centerPoint = new Point(e.getX(), e.getY());
			} else {
				centerPoint.setLocation(e.getX(), e.getY());
			}
		}
		
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// Update table
		if (parent.setCollision.getModel().isSelected()) {
			if (centerPoint == null) {
				JOptionPane.showMessageDialog(parent, "Please set center point first");
				return;
			}
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < points.size(); i++) {
				// recalculate points relative to the center point
				sb.append((points.get(i).x - centerPoint.x) + ":" + (centerPoint.y- points.get(i).y));
				if (i != points.size() - 1) {
					sb.append(";");
				}
			}
			
			parent.table.getModel().setValueAt(sb.toString(), parent.selectedObjectId, 2);
		} else if (parent.setCenterPoint.getModel().isSelected()) {
			if (centerPoint != null) {
				int textureHeight = Integer.parseInt((String) parent.table.getModel().getValueAt(parent.selectedObjectId, 4));
				
				// Invert y because of different coordinates in libgdx
				parent.table.getModel().setValueAt(centerPoint.x + ":" + (textureHeight - centerPoint.y), parent.selectedObjectId, 5);
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (parent.setCollision.getModel().isSelected()) {
			Rectangle r = new Rectangle();
			for (Point p : points) {
				r.setBounds(p.x-5, p.y-5, 10, 10);
				if (r.contains(e.getX(), e.getY())) {
					p.setLocation(e.getX(), e.getY());
					break;
				}
			}
		} else if (parent.setCenterPoint.getModel().isSelected()) {
			if (centerPoint == null) {
				centerPoint = new Point(e.getX(), e.getY());
			} else {
				centerPoint.setLocation(e.getX(), e.getY());
			}
		}

		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
