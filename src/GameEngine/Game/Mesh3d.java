//package GameEngine.Game;
//
//import GameEngine.Engine.GameItem;
//import GameEngine.Engine.Graph.Camera;
//import GameEngine.Engine.Graph.Mesh;
//import GameEngine.Engine.IGameLogic;
//import GameEngine.Engine.MouseInput;
//import GameEngine.Engine.Window;
//import org.joml.Math;
//import org.joml.Vector2f;
//import org.joml.Vector3d;
//import org.joml.Vector3f;
//import org.joml.*;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import static org.lwjgl.glfw.GLFW.*;
//
//
//public class Mesh3d implements IGameLogic {
//
//    private int displxInc = 0;
//
//    private int displyInc = 0;
//
//    private int displzInc = 0;
//
//    private int scaleInc = 0;
//
//
//    private GameItem[] gameItems;
//    private final Camera camera;
//    private final Vector3f cameraInc;
//    private static final float CAMERA_POS_STEP = 0.05f;
//    private static final float MOUSE_SENSITIVITY = 0.2f;
//
//    public Mesh3d() {
//        camera = new Camera();
//        cameraInc = new Vector3f();
//    }
//
//    @Override
//    public void init() throws Exception {
//        /*renderer.init("/resources/vertex.vert","/resources/fragment.frag");*/
//        // Create the Mesh
//        ArrayList<Vector3d> positions = new ArrayList<>(Arrays.asList(
//                new Vector3d(-0.5f, 0.5f, 0.5f),
//                new Vector3d(-0.5f, -0.5f, 0.5f),
//                new Vector3d(0.5f, -0.5f, 0.5f),
//                new Vector3d(0.5f, 0.5f, 0.5f),
//                new Vector3d(-0.5f, 0.5f, -0.5f),
//                new Vector3d(0.5f, 0.5f, -0.5f),
//                new Vector3d(-0.5f, -0.5f, -0.5f),
//                new Vector3d(0.5f, -0.5f, -0.5f),
//                new Vector3d(-0.5f, 0.5f, -0.5f),
//                new Vector3d(0.5f, 0.5f, -0.5f),
//                new Vector3d(-0.5f, 0.5f, 0.5f),
//                new Vector3d(0.5f, 0.5f, 0.5f),
//                new Vector3d(0.5f, 0.5f, 0.5f),
//                new Vector3d(0.5f, -0.5f, 0.5f),
//                new Vector3d(-0.5f, 0.5f, 0.5f),
//                new Vector3d(-0.5f, -0.5f, 0.5f),
//                new Vector3d(-0.5f, -0.5f, -0.5f),
//                new Vector3d(0.5f, -0.5f, -0.5f),
//                new Vector3d(-0.5f, -0.5f, 0.5f),
//                new Vector3d(0.5f, -0.5f, 0.5f)
//        ));
//        ArrayList<Integer> indices = new ArrayList<>(Arrays.asList(
//                // Front face
//                0, 1, 3, 3, 1, 2,
//                // Top Face
//                8, 10, 11, 9, 8, 11,
//                // Right face
//                12, 13, 7, 5, 12, 7,
//                // Left face
//                14, 15, 6, 4, 14, 6,
//                // Bottom face
//                16, 18, 19, 17, 16, 19,
//                // Back face
//                4, 6, 7, 5, 4, 7
//        ));
//        Mesh mesh = new Mesh(positions, indices);
//        mesh.load();
//        GameItem gameItem = new GameItem(mesh);
//        gameItem.setPosition(0, 0, 0);
//        gameItem.getRenderer().init("/resources/vertex.vert","/resources/fragment.frag");
//        gameItems = new GameItem[2];
//        gameItems[0]=gameItem;
//        for (Vector3d i : positions){
//            i = i.add( new Vector3d(0,0,10));
//        }
//
//        mesh = new Mesh(positions, indices);
//        mesh.load();
//        gameItem = new GameItem(mesh);
//        gameItem.setPosition(0, 0, 10);
//        gameItem.getRenderer().init("/resources/vertex.vert","/resources/fragment.frag");
//        gameItems[1]=gameItem;
//        camera.setPosition(3,0,0);
//
//    }
//
//    @Override
//    public void input(Window window, MouseInput mouseInput) {
//        cameraInc.set(0, 0, 0);
//        if (window.isKeyPressed(GLFW_KEY_W)) {
//            cameraInc.z = -1;
//        } else if (window.isKeyPressed(GLFW_KEY_S)) {
//            cameraInc.z = 1;
//        }
//        if (window.isKeyPressed(GLFW_KEY_A)) {
//            cameraInc.x = -1;
//        } else if (window.isKeyPressed(GLFW_KEY_D)) {
//            cameraInc.x = 1;
//        }
//        if (window.isKeyPressed(GLFW_KEY_Z)) {
//            cameraInc.y = -1;
//        } else if (window.isKeyPressed(GLFW_KEY_X)) {
//            cameraInc.y = 1;
//        }
//        if(window.isKeyPressed(GLFW_KEY_E)){
//            camera.setRotateSumbu(true);
//        }
//        if(window.isKeyPressed(GLFW_KEY_Q)){
//            camera.setRotateSumbu(false);
//        }
//    }
////    @Override
////    public void input(Window window, MouseInput mouseInput) {
////        displyInc = 0;
////        displxInc = 0;
////        displzInc = 0;
////        scaleInc = 0;
////        if (window.isKeyPressed(GLFW_KEY_UP)) {
////            displyInc = 1;
////        } else if (window.isKeyPressed(GLFW_KEY_DOWN)) {
////            displyInc = -1;
////        } else if (window.isKeyPressed(GLFW_KEY_LEFT)) {
////            displxInc = -1;
////        } else if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
////            displxInc = 1;
////        } else if (window.isKeyPressed(GLFW_KEY_A)) {
////            displzInc = -1;
////        } else if (window.isKeyPressed(GLFW_KEY_Q)) {
////            displzInc = 1;
////        } else if (window.isKeyPressed(GLFW_KEY_Z)) {
////            scaleInc = -1;
////        } else if (window.isKeyPressed(GLFW_KEY_X)) {
////            scaleInc = 1;
////        }
////    }
//
////    @Override
////    public void update(MouseInput mouseInput) {
////        for (GameItem gameItem : gameItems) {
////            // Update position
////            Vector3f itemPos = gameItem.getPosition();
////            float posx = itemPos.x + displxInc * 0.01f;
////            float posy = itemPos.y + displyInc * 0.01f;
////            float posz = itemPos.z + displzInc * 0.01f;
////            gameItem.setPosition(posx, posy, posz);
////
////            // Update scale
////            float scale = gameItem.getScale();
////            scale += scaleInc * 0.05f;
////            if (scale < 0) {
////                scale = 0;
////            }
////            gameItem.setScale(scale);
////
////            // Update rotation angle
////            float rotation = gameItem.getRotation().x + 1.5f;
////            if (rotation > 360) {
////                rotation = 0;
////            }
////            gameItem.setRotation(rotation, rotation, rotation);
////        }
////    }
//    @Override
//    public void update(MouseInput mouseInput) {
//        // Update camera position
////        camera.movePosition(cameraInc.x * CAMERA_POS_STEP,
////                cameraInc.y * CAMERA_POS_STEP,
////                cameraInc.z * CAMERA_POS_STEP);
//        //Update camera free move
////        System.out.println(glfwGetTime());
//        if(camera.isRotateSumbu()){
//            gameItems[1].setPosition((float) (Math.cos(Math.toRadians(glfwGetTime()*10)) * camera.getRadius()),
//                    (float) 0, (float) (Math.sin(Math.toRadians(glfwGetTime()*10)) * camera.getRadius())-10);
////            camera.setPosition(0,
////                    (float) 0, 0);
//           gameItems[1].setRotation(0,-90-(float) glfwGetTime() * camera.getRadius(),0);
//            System.out.print(glfwGetTime() + " "+Math.sin(glfwGetTime()) * camera.getRadius() + " ");
//            System.out.println(Math.cos(glfwGetTime()) * camera.getRadius());
//        }
//        else{
//            camera.movePosition(cameraInc.x * CAMERA_POS_STEP,
//                    1 * CAMERA_POS_STEP,
//                    cameraInc.z * CAMERA_POS_STEP);
//            // Update camera based on mouse
////        if (mouseInput.isRightButtonPressed()) {
//            Vector2f rotVec = mouseInput.getDisplVec();
//            camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY,
//                    rotVec.y * MOUSE_SENSITIVITY, 0);
//            System.out.println(camera.getRotation().x);
////        }
//        }
//
//
//
//        //Update FOV Camera
//        camera.setFOV(mouseInput.getScrollYOffset());
//        mouseInput.setScrollYOffset(0);
//    }
//    @Override
//    public void render(Window window) {
//        for (GameItem gameItem : gameItems) {
//            gameItem.render(window,camera);
//        }
//    }
//
//    @Override
//    public void cleanup() {
//        for (GameItem gameItem : gameItems) {
//            gameItem.cleanup();
//        }
//    }
//
//}
