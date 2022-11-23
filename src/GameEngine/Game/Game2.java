//package GameEngine.Game;
//
//import GameEngine.Engine.Graph.Mesh;
//import GameEngine.Engine.Renderer;
//import GameEngine.Engine.IGameLogic;
//import GameEngine.Engine.MouseInput;
//import GameEngine.Engine.Window;
//import org.joml.Vector3d;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
//import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;
//
//public class Game2 implements IGameLogic {
//    private int direction = 0;
//
//    private float color = 0.0f;
//
//    private final Renderer renderer;
//
//    private Mesh mesh;
//    ArrayList<Vector3d> positions2 = new ArrayList<>(Arrays.asList(
//            new Vector3d(-0.5f, 0.5f, 0.0f),
//            new Vector3d(-0.5f, -0.5f, 0.0f),
//            new Vector3d(0.5f, -0.5f, 0.0f),
//            new Vector3d(0.5f, 0.5f, 0.0f)
//    ));
//    ArrayList<Integer> indices2 = new ArrayList<>(Arrays.asList(0, 1, 3, 3, 1, 2));
//
//    public Game2() {
//        renderer = new Renderer();
//    }
//
//    @Override
//    public void init() throws Exception {
//        mesh = new Mesh(positions2, indices2,"/resources/vertex.vert","/resources/fragment.frag");
//    }
//
//    @Override
//    public void input(Window window, MouseInput mouseInput) {
//        if (window.isKeyPressed(GLFW_KEY_UP)) {
//            direction = 1;
//        } else if (window.isKeyPressed(GLFW_KEY_DOWN)) {
//            direction = -1;
//        } else {
//            direction = 0;
//        }
//    }
//
//    @Override
//    public void update(MouseInput mouseInput) {
//        color += direction * 0.01f;
//        if (color > 1) {
//            color = 1.0f;
//        } else if (color < 0) {
//            color = 0.0f;
//        }
//    }
//
//    @Override
//    public void render(Window window) {
//        window.setClearColor(color, color, color, 0.0f);
//        renderer.render(window,null);
//    }
//
//    @Override
//    public void cleanup() {
//        renderer.cleanup();
//        mesh.cleanUp();
//    }
//}
