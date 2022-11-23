package GameEngine.Game;

import GameEngine.Engine.GameItem;
import GameEngine.Engine.Graph.Camera;
import GameEngine.Engine.Graph.Mesh;
import GameEngine.Engine.IGameLogic;
import GameEngine.Engine.MouseInput;
import GameEngine.Engine.Window;
import org.joml.Math;
import org.joml.Vector2f;
import org.joml.Vector3d;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;


public class Mesh3dPhong implements IGameLogic {

    private GameItem[] gameItems;
    private final Camera camera;
    private final Vector3f cameraInc;
    private static final float CAMERA_POS_STEP = 0.05f;
    private static final float MOUSE_SENSITIVITY = 0.2f;

    public Mesh3dPhong() {
        camera = new Camera();
        cameraInc = new Vector3f();
    }

    @Override
    public void init() throws Exception {
        // Create the Mesh
        ArrayList<Vector3d> positions = new ArrayList<>(Arrays.asList(
                new Vector3d(-0.5f, -0.5f, -0.5f),
                new Vector3d(0.5f, -0.5f, -0.5f),
                new Vector3d(0.5f,  0.5f, -0.5f),
                new Vector3d(0.5f,  0.5f, -0.5f),
                new Vector3d(-0.5f,  0.5f, -0.5f),
                new Vector3d(-0.5f, -0.5f, -0.5f),

                new Vector3d(-0.5f, -0.5f,  0.5f),
                new Vector3d(0.5f, -0.5f,  0.5f),
                new Vector3d(0.5f,  0.5f,  0.5f),
                new Vector3d(0.5f,  0.5f,  0.5f),
                new Vector3d(-0.5f,  0.5f,  0.5f),
                new Vector3d(-0.5f, -0.5f,  0.5f),

                new Vector3d(-0.5f,  0.5f,  0.5f),
                new Vector3d(-0.5f,  0.5f, -0.5f),
                new Vector3d(-0.5f, -0.5f, -0.5f),
                new Vector3d(-0.5f, -0.5f, -0.5f),
                new Vector3d(-0.5f, -0.5f,  0.5f),
                new Vector3d(-0.5f,  0.5f,  0.5f),

                new Vector3d(0.5f,  0.5f,  0.5f),
                new Vector3d(0.5f,  0.5f, -0.5f),
                new Vector3d(0.5f, -0.5f, -0.5f),
                new Vector3d(0.5f, -0.5f, -0.5f),
                new Vector3d(0.5f, -0.5f,  0.5f),
                new Vector3d(0.5f,  0.5f,  0.5f),

                new Vector3d(-0.5f, -0.5f, -0.5f),
                new Vector3d(0.5f, -0.5f, -0.5f),
                new Vector3d( 0.5f, -0.5f,  0.5f),
                new Vector3d(0.5f, -0.5f,  0.5f),
                new Vector3d(-0.5f, -0.5f,  0.5f),
                new Vector3d(-0.5f, -0.5f, -0.5f),

                new Vector3d(-0.5f,  0.5f, -0.5f),
                new Vector3d(0.5f,  0.5f, -0.5f),
                new Vector3d(0.5f,  0.5f,  0.5f),
                new Vector3d(0.5f,  0.5f,  0.5f),
                new Vector3d(-0.5f,  0.5f,  0.5f),
                new Vector3d(-0.5f,  0.5f, -0.5f)
        ));
        ArrayList<Vector3d> normalPositions = new ArrayList<>(Arrays.asList(
                new Vector3d(0.0f,  0.0f, -1.0f),
                new Vector3d(0.0f,  0.0f, -1.0f),
                new Vector3d(0.0f,  0.0f, -1.0f),
                new Vector3d(0.0f,  0.0f, -1.0f),
                new Vector3d(0.0f,  0.0f, -1.0f),
                new Vector3d(0.0f,  0.0f, -1.0f),

                new Vector3d(0.0f,  0.0f,  1.0f),
                new Vector3d(0.0f,  0.0f,  1.0f),
                new Vector3d(0.0f,  0.0f,  1.0f),
                new Vector3d(0.0f,  0.0f,  1.0f),
                new Vector3d(0.0f,  0.0f,  1.0f),
                new Vector3d(0.0f,  0.0f,  1.0f),

                new Vector3d(-1.0f,  0.0f,  0.0f),
                new Vector3d(-1.0f,  0.0f,  0.0f),
                new Vector3d(-1.0f,  0.0f,  0.0f),
                new Vector3d(-1.0f,  0.0f,  0.0f),
                new Vector3d(-1.0f,  0.0f,  0.0f),
                new Vector3d(-1.0f,  0.0f,  0.0f),

                new Vector3d(1.0f,  0.0f,  0.0f),
                new Vector3d(1.0f,  0.0f,  0.0f),
                new Vector3d(1.0f,  0.0f,  0.0f),
                new Vector3d(1.0f,  0.0f,  0.0f),
                new Vector3d(1.0f,  0.0f,  0.0f),
                new Vector3d(1.0f,  0.0f,  0.0f),

                new Vector3d(0.0f, -1.0f,  0.0f),
                new Vector3d(0.0f, -1.0f,  0.0f),
                new Vector3d( 0.0f, -1.0f,  0.0f),
                new Vector3d(0.0f, -1.0f,  0.0f),
                new Vector3d(0.0f, -1.0f,  0.0f),
                new Vector3d(0.0f, -1.0f,  0.0f),

                new Vector3d(0.0f,  1.0f,  0.0f),
                new Vector3d(0.0f,  1.0f,  0.0f),
                new Vector3d(0.0f,  1.0f,  0.0f),
                new Vector3d(0.0f,  1.0f,  0.0f),
                new Vector3d(0.0f,  1.0f,  0.0f),
                new Vector3d(0.0f,  1.0f,  0.0f)
        ));
        ArrayList<Integer> indices = new ArrayList<>();
        Mesh mesh = new Mesh(positions, indices,normalPositions);
        mesh.load();
        GameItem gameItem = new GameItem(mesh);
        gameItem.setPosition(0, 0, 0);
        gameItem.getRenderer().init("/resources/vertex.vert","/resources/fragmentlight.frag");
        gameItems = new GameItem[2];
        gameItems[0]=gameItem;

        mesh = new Mesh(positions, indices,normalPositions);
        mesh.load();
        gameItem = new GameItem(mesh);
        gameItem.setPosition(5.0f,5.0f,0.0f);
        gameItem.getRenderer().init("/resources/vertex.vert","/resources/fragmentlight.frag");
        gameItems[1]=gameItem;

        camera.setPosition(3,0,0);

    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        cameraInc.set(0, 0, 0);
        if (window.isKeyPressed(GLFW_KEY_W)) {
            cameraInc.z = -1;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            cameraInc.z = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            cameraInc.x = -1;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            cameraInc.x = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_Z)) {
            cameraInc.y = -1;
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            cameraInc.y = 1;
        }
        if(window.isKeyPressed(GLFW_KEY_E)){
            camera.setRotateSumbu(true);
        }
        if(window.isKeyPressed(GLFW_KEY_Q)){
            camera.setRotateSumbu(false);
        }
    }

//    @Override
//    public void update(MouseInput mouseInput) {
//        for (GameItem gameItem : gameItems) {
//            // Update position
//            Vector3f itemPos = gameItem.getPosition();
//            float posx = itemPos.x + displxInc * 0.01f;
//            float posy = itemPos.y + displyInc * 0.01f;
//            float posz = itemPos.z + displzInc * 0.01f;
//            gameItem.setPosition(posx, posy, posz);
//
//            // Update scale
//            float scale = gameItem.getScale();
//            scale += scaleInc * 0.05f;
//            if (scale < 0) {
//                scale = 0;
//            }
//            gameItem.setScale(scale);
//
//            // Update rotation angle
//            float rotation = gameItem.getRotation().x + 1.5f;
//            if (rotation > 360) {
//                rotation = 0;
//            }
//            gameItem.setRotation(rotation, rotation, rotation);
//        }
//    }
    @Override
    public void update(MouseInput mouseInput) {
        //Update camera free move
        if(camera.isRotateSumbu()){
            gameItems[1].setPosition((float) (Math.cos(Math.toRadians(glfwGetTime()*10)) * camera.getRadius()),
                    (float) 0, (float) (Math.sin(Math.toRadians(glfwGetTime()*10)) * camera.getRadius())-10);
           gameItems[1].setRotation(0,-90-(float) glfwGetTime() * camera.getRadius(),0);
        }
        else{
            camera.movePosition(cameraInc.x * CAMERA_POS_STEP, cameraInc.y * CAMERA_POS_STEP, cameraInc.z * CAMERA_POS_STEP);
            Vector2f rotVec = mouseInput.getDisplVec();
            camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY,
                    rotVec.y * MOUSE_SENSITIVITY, 0);
        }
        //Update FOV Camera
        camera.setFOV(mouseInput.getScrollYOffset());
        mouseInput.setScrollYOffset(0);
    }
    @Override
    public void render(Window window) {
        for (GameItem gameItem : gameItems) {
            gameItem.render(window,camera);
        }
    }

    @Override
    public void cleanup() {
        for (GameItem gameItem : gameItems) {
            gameItem.cleanup();
        }
    }

}
