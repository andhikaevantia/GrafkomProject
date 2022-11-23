package GameEngine.Engine.Graph;

import org.joml.Vector3d;
import org.joml.Vector3f;

public class Camera {

    private final Vector3f position;
    
    private final Vector3f rotation;

    private float FOV = 60;

    private float radius = 10.0f;
    private boolean rotateSumbu = false;
    public Camera() {
        position = new Vector3f();
        rotation = new Vector3f(0.0f,0.0f,0.0f);
    }
    
    public Camera(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(float x, float y, float z) {
        position.x = x;
        position.y = y;
        position.z = z;
    }
    
    public void movePosition(float offsetX, float offsetY, float offsetZ) {
        if ( offsetZ != 0 ) {
            position.x += (float)Math.sin(Math.toRadians(rotation.y)) * -1.0f * offsetZ;
            position.z += (float)Math.cos(Math.toRadians(rotation.y)) * offsetZ;
        }
        if ( offsetX != 0) {
            position.x += (float)Math.sin(Math.toRadians(rotation.y - 90)) * -1.0f
                    * offsetX;
            position.z += (float)Math.cos(Math.toRadians(rotation.y - 90)) * offsetX;
        }
        position.y += offsetY;
    }

//    public void movePosition(float offsetX, float offsetY, float offsetZ) {
//        if ( offsetZ != 0 ) {
//            position.x += (float)Math.sin(Math.toRadians(rotation.y)) * -1.0f * offsetZ;
//            position.z += (float)Math.cos(Math.toRadians(rotation.y)) * offsetZ;
//            if(offsetZ < 0){
//                position.y += -offsetY * Math.toRadians(rotation.x);
//            }
//            if(offsetZ > 0){
//                position.y += offsetY * Math.toRadians(rotation.x);
//            }
//
//        }
//        if ( offsetX != 0) {
//            position.x += (float)Math.sin(Math.toRadians(rotation.y - 90)) * -1.0f
//                    * offsetX;
//            position.z += (float)Math.cos(Math.toRadians(rotation.y - 90)) * offsetX;
//        }
//
//    }

    public Vector3f getRotation() {
        return rotation;
    }
    
    public void setRotation(float x, float y, float z) {
        rotation.x = x;
        rotation.y = y;
        rotation.z = z;
    }
    public void moveRotation(float offsetX, float offsetY, float offsetZ) {
        rotation.x += offsetX;
        rotation.y += offsetY;
        rotation.z += offsetZ;
        if (rotation.x > 89.0f) {
            rotation.x = 89.0f;
        }
        if (rotation.x < -89.0f) {
            rotation.x = -89.0f;
        }
    }
//    public void moveRotation(float offsetX, float offsetY, float offsetZ) {
//        rotation.x += offsetX;
//        rotation.y += offsetY;
//        rotation.z += offsetZ;
//    }

    public void setFOV(float yoffset) {
        FOV -= yoffset * 0.5;
        if (FOV < 1.0f)
            FOV = 1.0f;
        if (FOV > 45.0f)
            FOV = 45.0f;
    }

    public float getFOV() {
        return (float)Math.toRadians(FOV);
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    public void setRotateSumbu(boolean rotateSumbu) {
        this.rotateSumbu = rotateSumbu;
    }

    public boolean isRotateSumbu() {
        return rotateSumbu;
    }
}