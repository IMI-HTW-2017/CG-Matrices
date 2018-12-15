package ab3.objects;

import ab3.math.Matrix4;
import ab3.math.Vector3;
import lenz.opengl.ShaderProgram;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public abstract class Object {

    float[] vertices;
    float[] normals;
    float[] uvs;

    private Matrix4 modelMatrix;
    private Matrix4 viewMatrix;

    private String shader;
    private ShaderProgram shaderProgram;

    Object(Vector3 position, Vector3 rotation, Vector3 scale, String shader) {
        this.shader = shader;

        modelMatrix = new Matrix4();
        viewMatrix = new Matrix4();

        viewMatrix.translate(position.x(), position.y(), position.z());
        viewMatrix.rotateX(rotation.x()).rotateY(rotation.y()).rotateZ(rotation.z());
        viewMatrix.scale(scale.x(), scale.y(), scale.z());
    }

    void init() {
        shaderProgram = new ShaderProgram(shader);
        glUseProgram(shaderProgram.getId());

        int vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        createVBO(vertices, 0, 3);
        createVBO(normals, 1, 3);
        if (uvs != null)
            createVBO(uvs, 2, 2);

        //View matrix & projection matrix
        int uniformViewMatrixID = glGetUniformLocation(shaderProgram.getId(), "viewMatrix");
        int uniformProjectionMatrixID = glGetUniformLocation(shaderProgram.getId(), "projectionMatrix");
        glUniformMatrix4fv(uniformViewMatrixID, false, viewMatrix.getValuesAsArray());
        glUniformMatrix4fv(uniformProjectionMatrixID, false, new Matrix4(0.5F, 100F).getValuesAsArray());
    }

    private void createVBO(float[] values, int index, int size) {
        int vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, values, GL_STATIC_DRAW);
        glVertexAttribPointer(index, size, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(index);
    }

    public Matrix4 getModelMatrix() {
        return modelMatrix;
    }

    public ShaderProgram getShaderProgram() {
        return shaderProgram;
    }
}
