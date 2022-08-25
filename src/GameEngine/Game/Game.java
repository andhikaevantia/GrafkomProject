package GameEngine.Game;

import GameEngine.Engine.Graph.Mesh;
import GameEngine.Engine.Graph.Renderer;
import GameEngine.Engine.IGameLogic;
import GameEngine.Engine.Window;
import org.joml.Vector3d;

import java.util.ArrayList;
import java.util.Arrays;

public class Game implements IGameLogic {
    ArrayList<Vector3d> positions2 = new ArrayList<>(Arrays.asList(
            new Vector3d(-0.3f, 0.3f, 0.0f),
            new Vector3d(-0.3f, -0.3f, 0.0f),
            new Vector3d(0.3f, -0.3f, 0.0f),
            new Vector3d(0.3f, 0.3f, 0.0f)
    ));
    ArrayList<Integer> indices2 = new ArrayList<>(Arrays.asList(0, 1, 3, 3, 1, 2));
    ArrayList<Vector3d> positions3 = new ArrayList<>(Arrays.asList(
            new Vector3d(0.5f, 0.5f, 0.0f),
            new Vector3d(0.5f, 0.8f, 0.0f),
            new Vector3d(0.8f, 0.8f, 0.0f),
            new Vector3d(0.8f, 0.5f, 0.0f)
    ));
    ArrayList<Integer> indices3 = new ArrayList<>(Arrays.asList(0, 1, 3, 3, 1, 2));

    private Mesh mesh;
    private Mesh mesh2;

    public Game() {
    }
    @Override
    public void init() throws Exception {
        mesh = new Mesh(positions2, indices2,"/resources/vertex.vert","/resources/fragment.frag");
        mesh2 = new Mesh(positions3, indices3,"/resources/vertex.vert","/resources/fragment.frag");
    }

    @Override
    public void input(Window window) {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Window window) {
        mesh.render(window);
        mesh2.render(window);
    }

    @Override
    public void cleanup() {
        mesh.cleanUp();
        mesh2.cleanUp();
    }
}
