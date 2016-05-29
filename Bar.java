/**
 * Copyright 2016 Filippo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package valuebar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 * @author Filippo
 * @version 1.0.0
 * @since 5/22/2016
 */
public class Bar extends JPanel implements KeyListener, MouseListener, MouseMotionListener {

    private int x = 500;
    private boolean isDragging;

    public Bar() {
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.CYAN);
        setFocusable(true);
        addKeyListener(Bar.this);
        addMouseListener(Bar.this);
        addMouseMotionListener(Bar.this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.red);
        
        int percentage = calculatePercentage(x);
        graphics2D.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        graphics2D.drawString(percentage + "%", getWidth()/2, 50);

        graphics2D.fillRect(0, ((getHeight() / 2) - 100), x, 200);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        seekBar(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        isDragging = true;
        seekBar(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isDragging = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse Entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void seekBar(MouseEvent e) {
        float mX = e.getX();
        float mY = e.getY();

        if (checkY(mY) && checkX(mX)) {
            x = (int) mX;
            invalidate();
            repaint();
        } else {
            playSound();
        }

        System.out.println("X :: " + mX + " Y :: " + mY);
    }

    private boolean checkY(float mY) {
        return (mY >= ((getHeight() / 2) - 100)) && (mY <= ((getHeight() / 2) + 100));
    }
    
    private boolean checkX(float mX) {
        return (mX >= 0) && (mX <= getWidth());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isDragging) {
            seekBar(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    private int calculatePercentage(int x) {
        double value = (x / 500.00);
        double percentage = value * 100;
        System.out.println("X :: " + x + " value :: " + value + " percentage " + percentage);
        return (int) percentage;   
    }

    private void playSound() {
        Toolkit.getDefaultToolkit().beep();
    }
}
