package GameEngine.Engine.Graph;

import GameEngine.Engine.Graph.Mesh;
import GameEngine.Engine.Graph.ShaderProgram;
import GameEngine.Engine.Utils;
import GameEngine.Engine.Window;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Renderer {
    private ShaderProgram shaderProgram;
    public Renderer() {
    }

    public void init(String VertLocation,String FragLocation) throws Exception {
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Utils.loadResource(VertLocation));
        shaderProgram.createFragmentShader(Utils.loadResource(FragLocation));
        shaderProgram.link();
    }
    public void render(Window window, Mesh mesh,Integer count_Line) {

        if (window.isResized()) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        shaderProgram.bind();

        // Draw the mesh
        glBindVertexArray(mesh.getVaoId());
        if(count_Line != 0){
            glDrawArrays(GL_LINE_STRIP,0,count_Line);
        }
        else {
            glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);
        }

        // Restore state
        glBindVertexArray(0);

        shaderProgram.unbind();
    }
    public void cleanup() {
        if (shaderProgram != null) {
            shaderProgram.cleanup();
        }
    }
}
