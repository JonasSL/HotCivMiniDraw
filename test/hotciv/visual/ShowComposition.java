package hotciv.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

/** Template code for exercise FRS 36.44.

 This source code is from the book
 "Flexible, Reliable Software:
 Using Patterns and Agile Development"
 published 2010 by CRC Press.
 Author:
 Henrik B Christensen
 Computer Science Department
 Aarhus University

 This source code is provided WITHOUT ANY WARRANTY either
 expressed or implied. You may study, use, modify, and
 distribute it for non-commercial purposes. For any
 commercial use, see http://www.baerbak.com/
 */
public class ShowComposition {

    public static void main(String[] args) {
        Game game = new StubGame2();

        DrawingEditor editor =
                new MiniDrawApplication( "Click and/or drag any item to see all game actions",
                        new HotCivFactory4(game) );
        editor.open();
        editor.showStatus("Click and drag any item to see Game's proper response.");

        // Replace the setting of the tool with your CompositionTool implementation.
        editor.setTool( new CompositionTool(game, editor) );
    }
}

class CompositionTool extends SelectionTool {
    Game game;
    EndOfTurnTool endOfTurnTool;
    UnitMoveTool unitMoveTool;
    ActionTool actionTool;
    FocusTool focusTool;

    public CompositionTool(Game g, DrawingEditor e) {
        super(e);
        game = g;
        endOfTurnTool = new EndOfTurnTool(g);
        unitMoveTool = new UnitMoveTool(e,g);
        actionTool = new ActionTool(g);
        focusTool = new FocusTool(g);
    }
    public void mouseDown(MouseEvent e, int x, int y) {
        unitMoveTool.mouseDown(e,x,y);

        endOfTurnTool.mouseDown(e,x,y);
        actionTool.mouseDown(e,x,y);
        focusTool.mouseDown(e,x,y);
    }

    public void mouseUp(MouseEvent e, int x, int y) {
        unitMoveTool.mouseUp(e,x,y);
    }
}
