package GameEngine.Engine;

import GameEngine.Engine.Graph.Camera;
import GameEngine.Engine.Graph.Mesh;
import org.joml.Vector3f;

public class GameItem {

    private final Mesh mesh;

    private final Vector3f position;

    private float scale;

    private Vector3f rotation;

    private Renderer renderer;

    public GameItem(Mesh mesh) {
        this.mesh = mesh;
        position = new Vector3f(0,0,0);
        scale = 1;
        rotation = new Vector3f(0,0,0);
        renderer = new Renderer();
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(float x, float y, float z) {
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }

    public Mesh getMesh() {
        return mesh;
    }
    public Renderer getRenderer(){
        return renderer;
    }
    public void render(Window window, Camera camera){
        renderer.render(window, this,camera);
    }
    public void cleanup(){
        renderer.cleanup();
        mesh.cleanUp();
    }
}