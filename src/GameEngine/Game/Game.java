//package GameEngine.Game;
//
//import GameEngine.Engine.Graph.Mesh;
//import GameEngine.Engine.IGameLogic;
//import GameEngine.Engine.MouseInput;
//import GameEngine.Engine.Utils;
//import GameEngine.Engine.Window;
//import org.joml.Vector2d;
//import org.joml.Vector3d;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import static GameEngine.Engine.Utils.CreateCurveBezier;
//import static GameEngine.Engine.Utils.CreateLine;
//
//public class Game implements IGameLogic {
//    ArrayList<Vector3d> positions2 = new ArrayList<>(Arrays.asList(
////            new Vector3d(0.5f, 0.0f, 0.0f),
////            new Vector3d(0.5f, 0.8f, 0.0f),
////            new Vector3d(0.8f, 0.8f, 0.0f),
////            new Vector3d(0.8f, 0.5f, 0.0f)
//    ));
//    ArrayList<Integer> indices2 = new ArrayList<>();
//    ArrayList<Vector3d> positions3 = new ArrayList<>(Arrays.asList(
//            new Vector3d(0.5f, 0.5f, 0.0f),
//            new Vector3d(0.5f, 0.8f, 0.0f),
//            new Vector3d(0.8f, 0.8f, 0.0f),
//            new Vector3d(0.8f, 0.5f, 0.0f)
//    ));
//    ArrayList<Integer> indices3 = new ArrayList<>();
//
//    ArrayList<Vector3d> vertices = Utils.createCircle(new Vector3d(0.5, 0.5, 0.0), 0.25, 360);
//
//    private Mesh mesh;
//    private Vector2d previousPos;
//    private Mesh mesh2;
//
//    public Game() {
//    }
//    @Override
//    public void init() throws Exception {
//        positions2 = CreateLine(new Vector3d(0,0,0),new Vector3d(0,0.5,0));
//        mesh = new Mesh(positions2, indices2,"/resources/vertex.vert","/resources/fragment.frag");
//        mesh2 = new Mesh(vertices, indices3,"/resources/vertex.vert","/resources/fragment.frag");
//
//    }
//
//    @Override
//    public void input(Window window, MouseInput mouseInput) {
//
//    }
//
//    @Override
//    public void update(MouseInput mouseInput) throws Exception {
//        if(mouseInput.isLeftButtonPressed()){
//            positions2.add(new Vector3d(mouseInput.getCurrentPos().x,mouseInput.getCurrentPos().y,0));
//            mesh = new Mesh(positions2, indices2,"/resources/vertex.vert","/resources/fragment.frag");
//            mouseInput.setLeftButtonPressed(false);
//        }
//        if(positions2.size() >= 3){
//            positions3 = CreateCurveBezier(positions2);
//            mesh2 = new Mesh(positions3, indices3,"/resources/vertex.vert","/resources/fragment.frag");
//        }
//    }
//
//    @Override
//    public void render(Window window) {
//        if(positions2.size() != 0){
//            mesh.render();
//        }
//
////        mesh2.render(window, vertices.size());
//    }
//
//    @Override
//    public void cleanup() {
//        mesh.cleanUp();
//        mesh2.cleanUp();
//    }
//}
