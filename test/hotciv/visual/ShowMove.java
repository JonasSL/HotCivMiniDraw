package hotciv.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

/** Template code for exercise FRS 36.39.

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
public class ShowMove {
  
  public static void main(String[] args) {
    Game game = new StubGame2();

    DrawingEditor editor = 
      new MiniDrawApplication( "Move any unit using the mouse",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Move units to see Game's moveUnit method being called.");

    // Replace the setting of the tool with your UnitMoveTool implementation.
    editor.setTool( new UnitMoveTool(editor,game));
  }
}

class UnitMoveTool extends SelectionTool {
    private Game game;
    private Position fromPosition, toPosition;
    public UnitMoveTool(DrawingEditor editor, Game game) {
        super(editor);
        this.game = game;
    }
    public void mouseDown(MouseEvent e, int x, int y) {
        fromPosition = GfxConstants.getPositionFromXY(x,y);

        if (game.getUnitAt(fromPosition) != null) {
            super.mouseDown(e,x,y);
        }
    }

    public void mouseUp(MouseEvent e, int x, int y) {
        toPosition = GfxConstants.getPositionFromXY(x,y);

        int xc = toPosition.getRow();
        int yc = toPosition.getColumn();
        if (!(xc < GameConstants.WORLDSIZE && 0 <= xc)) {
            xc = fromPosition.getRow();
        }
        if (!(yc < GameConstants.WORLDSIZE && 0 <= yc)) {
            yc = fromPosition.getColumn();
        }
        toPosition = new Position(xc,yc);
        if (game.getUnitAt(fromPosition) != null) {
            game.moveUnit(fromPosition,toPosition);
        }

    }

}
