package GameEngine.Engine.Graph;

import GameEngine.Engine.Renderer;
import GameEngine.Engine.Window;
import org.joml.Vector3d;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Mesh {
    private int vaoId;

    private int posVboId;

    private int idxVboId;

    private int indexCount;

    private int vertexCount;

    private ArrayList<Vector3d> positions;
    private ArrayList<Integer> indices;
    private ArrayList<Vector3d> normals;
    private int normCount;
    private int postNboId;

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
    public Mesh(ArrayList<Vector3d> positions, ArrayList<Integer> indices, ArrayList<Vector3d> normals){
        this.positions = positions;
        this.indices = indices;
        this.normals = normals;
    }
    public int getIndexCount() {
        return indexCount;
    }
    public void load() throws Exception{
        FloatBuffer posBuffer = null;
        IntBuffer indicesBuffer = null;
        FloatBuffer normBuffer = null; //1
        try {
            indexCount = indices.size();
            vertexCount = positions.size();
            normCount = normals.size(); //2

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

            // Normal VBO
            postNboId = glGenBuffers();
            normBuffer = MemoryUtil.memAllocFloat(
                    normals.size()*3);
            normBuffer.put(ConvertListVectortoArray(normals)).flip();
            glBindBuffer(GL_ARRAY_BUFFER, postNboId);
            glBufferData(GL_ARRAY_BUFFER, normBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(1);
            glVertexAttribPointer(1, 3, GL_FLOAT,
                    false, 0, 0);

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
            if(normBuffer != null){
                MemoryUtil.memFree(normBuffer);
            }
        }
    }
    public int getVaoId() {
        return vaoId;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getNormCount(){
        return normCount;
    }

    public void cleanUp() {
        glDisableVertexAttribArray(0);

        // Delete the VBOs
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(posVboId);
        glDeleteBuffers(idxVboId);

        // Delete the VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
    }
    public void render(){
        // Draw the mesh
        glBindVertexArray(getVaoId());


        if(indices.size() != 0){
            glDrawElements(GL_TRIANGLES,
                    getIndexCount(), GL_UNSIGNED_INT, 0);
        }
        else{
            glDrawArrays(GL_TRIANGLES,0,
                    getVertexCount());
        }


        // Restore state
        glBindVertexArray(0);
    }


}
