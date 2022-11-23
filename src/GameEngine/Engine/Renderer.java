package GameEngine.Engine;

import GameEngine.Engine.Graph.Camera;
import GameEngine.Engine.Graph.Mesh;
import GameEngine.Engine.Graph.ShaderProgram;
import GameEngine.Engine.Graph.Transformation;
import GameEngine.Engine.Utils;
import GameEngine.Engine.Window;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3d;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.GL_VERTEX_PROGRAM_POINT_SIZE;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL32C.GL_PROGRAM_POINT_SIZE;

public class Renderer {

    private static final float Z_NEAR = 0.01f;

    private static final float Z_FAR = 1000.f;

    private final Transformation transformation;
    private ShaderProgram shaderProgram;
    public Renderer() {
        transformation = new Transformation();
    }

    public void init(String VertLocation,String FragLocation) throws Exception {
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Utils.loadResource(VertLocation));
        shaderProgram.createFragmentShader(Utils.loadResource(FragLocation));
        shaderProgram.link();

        // Create uniforms for world and projection matrices and texture
        shaderProgram.createUniform("projectionMatrix");
        shaderProgram.createUniform("model");
        shaderProgram.createUniform("view");
        shaderProgram.createUniform("objectColor");
        shaderProgram.createUniform("lightColor");
        shaderProgram.createUniform("lightPos");
        shaderProgram.createUniform("viewPos");
    }
    public void render(Window window, GameItem gameItem, Camera camera) {

        if (window.isResized()) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        shaderProgram.bind();

        // Update projection Matrix
        Matrix4f projectionMatrix = transformation.getProjectionMatrix(camera.getFOV(), window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);
        shaderProgram.setUniform("model", transformation.getWorldMatrix(gameItem.getPosition(),gameItem.getRotation(),gameItem.getScale()));
        shaderProgram.setUniform("view",
                transformation.getViewMatrix(camera));
        gameItem.getMesh().render();

        shaderProgram.setUniform3f("objectColor", new Vector3f(0.0f,1.0f,0.0f));
        shaderProgram.setUniform3f("lightColor", new Vector3f(1.0f,1.0f,1.0f));
        shaderProgram.setUniform3f("lightPos", new Vector3f(5.0f,5.0f,0.0f));
        shaderProgram.setUniform3f("viewPos", camera.getPosition());
        shaderProgram.unbind();
    }
    public void cleanup() {
        if (shaderProgram != null) {
            shaderProgram.cleanup();
        }
    }
}
