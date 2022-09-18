package GameEngine.Engine.Graph;

import GameEngine.Engine.Window;
import org.joml.Vector3d;
import org.lwjgl.system.MemoryUtil;

import java.lang.reflect.Array;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Mesh {
    private final int vaoId;

    private final int posVboId;

    private final int idxVboId;

    private final int vertexCount;
    private final Renderer renderer;

    public float[] ConvertListVectortoArray(ArrayList<Vector3d> input){
        float[] output = new float[input.size()*3];
        int counter = 0;
        for (Vector3d item:input) {
            output[counter++] = (float)item.x;
            output[counter++] = (float)item.y;
            output[counter++] = (float)item.z;
        }
        return output;
    }
    public Mesh(ArrayList<Vector3d> positions, ArrayList<Integer> indices,String VertLocation,String FragLocation) throws Exception {
        FloatBuffer posBuffer = null;
        IntBuffer indicesBuffer = null;
        try {
            vertexCount = indices.size();

            vaoId = glGenVertexArrays();
            glBindVertexArray(vaoId);

            // Position VBO
            posVboId = glGenBuffers();
            posBuffer = MemoryUtil.memAllocFloat(positions.size()*3);
            posBuffer.put(ConvertListVectortoArray(positions)).flip();
            glBindBuffer(GL_ARRAY_BUFFER, posVboId);
            glBufferData(GL_ARRAY_BUFFER, posBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(0);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

            // Index VBO
            idxVboId = glGenBuffers();
            indicesBuffer = MemoryUtil.memAllocInt(indices.size());
            indicesBuffer.put(indices.stream().mapToInt(i -> i).toArray()).flip();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, idxVboId);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        } finally {
            if (posBuffer != null) {
                MemoryUtil.memFree(posBuffer);
            }
            if (indicesBuffer != null) {
                MemoryUtil.memFree(indicesBuffer);
            }
        }
        renderer = new Renderer();
        renderer.init(VertLocation,FragLocation);
    }
    public int getVaoId() {
        return vaoId;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void cleanUp() {
        renderer.cleanup();
        glDisableVertexAttribArray(0);

        // Delete the VBOs
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(posVboId);
        glDeleteBuffers(idxVboId);

        // Delete the VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
    }
    public void render(Window window,Integer count_Line){
        renderer.render(window,this,count_Line);
    }


}
