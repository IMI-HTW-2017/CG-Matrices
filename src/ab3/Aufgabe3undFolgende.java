package ab3;

import static org.lwjgl.opengl.GL30.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Aufgabe3undFolgende extends AbstractOpenGLBase {

	private ShaderProgram shaderProgram;
	private Matrix4 transformationMatrix;

	private int counter;

	public static void main(String[] args) {
		new Aufgabe3undFolgende().start("CG Aufgabe 3", 700, 700);
	}

	@Override
	protected void init() {
		shaderProgram = new ShaderProgram("aufgabe3");
		glUseProgram(shaderProgram.getId());

		transformationMatrix = new Matrix4().translate(0, 0, -3);

		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen
		int vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);

		int vboID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboID);

		/*float[] vertices = new float[] {
				-0.5F, -0.5F,
				0.5F, -0.5F,
				-0.5F, 0.5F,

				-0.5F, 0.5F,
				0.5F, -0.5F,
				0.5F, 0.5F
		};*/

		//Cube
        float[] vertices = new float[] {
                //vorne
                -0.5F, -0.5F, 0.5F,
                0.5F, -0.5F, 0.5F,
                -0.5F, 0.5F, 0.5F,

                -0.5F, 0.5F, 0.5F,
                0.5F, -0.5F, 0.5F,
                0.5F, 0.5F, 0.5F,

                //hinten
                -0.5F, -0.5F, -0.5F,
                -0.5F, 0.5F, -0.5F,
                0.5F, -0.5F, -0.5F,

                -0.5F, 0.5F, -0.5F,
                0.5F, 0.5F, -0.5F,
                0.5F, -0.5F, -0.5F,

                //unten
                -0.5F, -0.5F, 0.5F,
                0.5F, -0.5F, -0.5F,
                0.5F, -0.5F, 0.5F,

                -0.5F, -0.5F, 0.5F,
                -0.5F, -0.5F, -0.5F,
                0.5F, -0.5F, -0.5F,

                //oben
                -0.5F, 0.5F, 0.5F,
                0.5F, 0.5F, 0.5F,
                0.5F, 0.5F, -0.5F,

                -0.5F, 0.5F, 0.5F,
                0.5F, 0.5F, -0.5F,
                -0.5F, 0.5F, -0.5F,

                //links
                -0.5F, 0.5F, 0.5F,
                -0.5F, 0.5F, -0.5F,
                -0.5F, -0.5F, 0.5F,

                -0.5F, -0.5F, 0.5F,
                -0.5F, 0.5F, -0.5F,
                -0.5F, -0.5F, -0.5F,

                //rechts
                0.5F, 0.5F, 0.5F,
                0.5F, -0.5F, 0.5F,
                0.5F, 0.5F, -0.5F,

                0.5F, -0.5F, 0.5F,
                0.5F, -0.5F, -0.5F,
                0.5F, 0.5F, -0.5F
        };

		glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(0);

		glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
		glEnable(GL_CULL_FACE); // backface culling aktivieren

        int uniformProjectionMatrixID = glGetUniformLocation(shaderProgram.getId(), "projectionMatrix");
        glUniformMatrix4fv(uniformProjectionMatrixID, false, new Matrix4(0.5F, 100F).getValuesAsArray());
	}

	@Override
	public void update() {
		// Transformation durchführen (Matrix anpassen)
        if (counter < 45)
            transformationMatrix.translate(0, 0, 3).rotateX(1).translate(0, 0, -3);
        else
            transformationMatrix.translate(0, 0, 3).rotateY(1).translate(0, 0, -3);

        counter = (counter + 1) % 90;
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		// Matrix an Shader übertragen
		// VAOs zeichnen
        int uniformTransformationMatrixID = glGetUniformLocation(shaderProgram.getId(), "transformationMatrix");
        glUniformMatrix4fv(uniformTransformationMatrixID, false, transformationMatrix.getValuesAsArray());

        glDrawArrays(GL_TRIANGLES, 0, 36);
	}

	@Override
	public void destroy() {
	}
}
