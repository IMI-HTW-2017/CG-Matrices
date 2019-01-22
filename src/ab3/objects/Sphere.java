package ab3.objects;

import ab3.math.Vector3;

public class Sphere extends Object {

    public Sphere(float x, float y, float z, String shader) {
        this(new Vector3(x, y, z), Vector3.zero(), Vector3.one(), shader);
    }

    public Sphere(Vector3 position, Vector3 rotation, Vector3 scale, String shader) {
        super(position, rotation, scale, shader);

        float val = (float) (Math.sqrt(3) / 4);

        vertices = new float[] {
                //front
                0.5f, val, 0,
                -0.5f, val, 0,
                0, -val, 0.5f,

                //left
                -0.5f, val, 0,
                0, -val, -0.5f,
                0, -val, 0.5f,

                //right
                0.5f, val, 0,
                0, -val, 0.5f,
                0, -val, -0.5f,

                //back
                -0.5f, val, 0,
                0.5f, val, 0,
                0, -val, -0.5f
        };

        for (int steps = 0; steps < 7; steps++) {
            float[] newVertices = new float[vertices.length * 4];

            for (int i = 0; i < vertices.length; i += 9) {
                Vector3 point1 = new Vector3(
                        vertices[i] + (vertices[i + 3] - vertices[i]) / 2f,
                        vertices[i + 1] + (vertices[i + 4] - vertices[i + 1]) / 2f,
                        vertices[i + 2] + (vertices[i + 5] - vertices[i + 2]) / 2f
                );
                Vector3 point2 = new Vector3(
                        vertices[i + 3] + (vertices[i + 6] - vertices[i + 3]) / 2f,
                        vertices[i + 4] + (vertices[i + 7] - vertices[i + 4]) / 2f,
                        vertices[i + 5] + (vertices[i + 8] - vertices[i + 5]) / 2f
                );
                Vector3 point3 = new Vector3(
                        vertices[i + 6] + (vertices[i] - vertices[i + 6]) / 2f,
                        vertices[i + 7] + (vertices[i + 1] - vertices[i + 7]) / 2f,
                        vertices[i + 8] + (vertices[i + 2] - vertices[i + 8]) / 2f
                );

                newVertices[i * 4] = vertices[i];
                newVertices[i * 4 + 1] = vertices[i + 1];
                newVertices[i * 4 + 2] = vertices[i + 2];
                newVertices[i * 4 + 3] = point1.x();
                newVertices[i * 4 + 4] = point1.y();
                newVertices[i * 4 + 5] = point1.z();
                newVertices[i * 4 + 6] = point3.x();
                newVertices[i * 4 + 7] = point3.y();
                newVertices[i * 4 + 8] = point3.z();

                newVertices[i * 4 + 9] = point1.x();
                newVertices[i * 4 + 10] = point1.y();
                newVertices[i * 4 + 11] = point1.z();
                newVertices[i * 4 + 12] = vertices[i + 3];
                newVertices[i * 4 + 13] = vertices[i + 4];
                newVertices[i * 4 + 14] = vertices[i + 5];
                newVertices[i * 4 + 15] = point2.x();
                newVertices[i * 4 + 16] = point2.y();
                newVertices[i * 4 + 17] = point2.z();

                newVertices[i * 4 + 19] = point3.y();
                newVertices[i * 4 + 18] = point3.x();
                newVertices[i * 4 + 20] = point3.z();
                newVertices[i * 4 + 21] = point2.x();
                newVertices[i * 4 + 22] = point2.y();
                newVertices[i * 4 + 23] = point2.z();
                newVertices[i * 4 + 24] = vertices[i + 6];
                newVertices[i * 4 + 25] = vertices[i + 7];
                newVertices[i * 4 + 26] = vertices[i + 8];

                newVertices[i * 4 + 27] = point1.x();
                newVertices[i * 4 + 28] = point1.y();
                newVertices[i * 4 + 29] = point1.z();
                newVertices[i * 4 + 30] = point2.x();
                newVertices[i * 4 + 31] = point2.y();
                newVertices[i * 4 + 32] = point2.z();
                newVertices[i * 4 + 33] = point3.x();
                newVertices[i * 4 + 34] = point3.y();
                newVertices[i * 4 + 35] = point3.z();
            }

            vertices = newVertices;

            for (int i = 0; i < vertices.length; i += 3) {
                Vector3 vector = new Vector3(vertices[i], vertices[i + 1], vertices[i + 2]);
                vector.normalize();
                vertices[i] = vector.x();
                vertices[i + 1] = vector.y();
                vertices[i + 2] = vector.z();
            }
        }

        normals = calculateObjectNormals(vertices);

        super.init();
    }
}
