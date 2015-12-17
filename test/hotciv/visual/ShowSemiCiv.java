package hotciv.visual;

import hotciv.framework.Game;
import hotciv.standard.Factories.SemiFactory;
import hotciv.standard.GameImpl;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class ShowSemiCiv {

    public static void main(String[] args) {
        Game game = new GameImpl(new SemiFactory());

        DrawingEditor editor =
                new MiniDrawApplication( "This is semi civ!",
                        new HotCivFactory4(game) );
        editor.open();
        editor.showStatus("You can play now!");

        // Replace the setting of the tool with your CompositionTool implementation.
        editor.setTool( new CompositionTool(game, editor) );
    }
}
