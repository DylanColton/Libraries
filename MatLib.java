package libraries;

public class MatLib {
	/* Make a better formatting */
	// Prints the matrix in the argument
	public static void printMatrix(double[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	// Returns the identity matrix for a given size (I is always square)
	public static double[][] identity(int size) {
		if(size < 1 || size > 10) {
			System.out.println("Invalid size selction");
			return identity(1);
		}
		
		double[][] identity = new double[size][size];
		for(int i = 0; i < identity.length; i++) {
			for(int j = 0; j < identity[i].length; j++) {
				identity[i][j] = 0;
				if(i == j) { identity[i][j] = 1; }
			}
		}
		return identity;
	}
	
	// Returns the submatrix of a matrix removing a given row and column
	public static double[][] submatrix(double[][] matrix, int row, int col) {
		if(matrix.length == 1 || matrix[0].length == 1) {
			System.out.println("Cannot reduce the given matrix any further");
			return identity(matrix.length);
		}
		if(row < 0 || row >= matrix.length) {
			System.out.println("Invalid row selection");
			return identity(matrix.length);
		}
		if(col < 0 || col >= matrix[0].length) {
			System.out.println("Invalid column selection");
			return identity(matrix[0].length);
		}
		
		double[][] submat = new double[matrix.length - 1][matrix[0].length - 1];
		int k = 0, l = 0;
		for(int i = 0; i < matrix.length; i++) {
			if(i == row) { continue; }
			for(int j = 0; j < matrix[0].length; j++) {
				if(j == col) { continue; }
				submat[k][l++] = matrix[i][j];
			}
			k++;
			l = 0;
		}
		
		return submat;
	}
	
	// Checks first if the matrices can be added, if they can't then the identity of matrix1.length is returned, otherwise, the addition is returned
	public static double[][] addMatrices(double[][] matrix1, double[][] matrix2) {
		if(matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
			System.out.println("These two matrices cannot be added");
			return identity(matrix1.length);
		}
		
		double[][] mat = new double[matrix1.length][matrix1[0].length];
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat[0].length; j++) {
				mat[i][j] = matrix1[i][j] + matrix2[i][j];
			}
		}
		return mat;
	}
	
	// Checks if the matrices can be subtracted, and if they can't, returns the identity of matrix.length, otherwise the subtraction is returned.
	// The subtraction is always performed as matrix1 - matrix2
	public static double[][] subtractMatrices(double[][] matrix1, double[][] matrix2) {
		if(matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
			System.out.println("These two matrices cannot be added");
			return identity(matrix1.length);
		}
		
		double[][] mat = new double[matrix1.length][matrix1[0].length];
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat[0].length; j++) {
				mat[i][j] = matrix1[i][j] - matrix2[i][j];
			}
		}
		return mat;
	}
	
	// Multiplies a matrix by a scalar
	public static double[][] scalarMultiply(double scalar, double[][] matrix) {
		double[][] mat = new double[matrix.length][matrix[0].length];
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat[0].length; j++) {
				mat[i][j] = matrix[i][j] * scalar;
			}
		}
		return mat;
	}
	
	// Checks if two matrices can be multiplied, if they can't, the identity of matrix1.length is returned, otherwise returns the multiplication of the matrices
	public static double[][] matrixMultiply(double[][] matrix1, double[][] matrix2) {
		if(matrix1[0].length != matrix2.length) {
			System.out.println("The matrices cannot be multiplied together");
			return identity(matrix1.length);
		}
		
		double[][] mat = new double[matrix1.length][matrix2[0].length];
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat[0].length; j++) {
				mat[i][j] = 0;
				for(int k = 0; k < matrix1[0].length; k++) {
					mat[i][j] += matrix1[i][k] * matrix2[k][j];
				}
			}
		}
		return mat;
	}
	
	public static double trace(double[][] matrix) {
		double tr = 0;
		for(int i = 0; i < min(matrix.length, matrix[0].length); i++) {
			tr += matrix[i][i];
		}
		return tr;
	}
	
	public static double determinant(double[][] matrix) {
		if(matrix.length == 2) {
			return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
		}
		double det = 0;
		for(int i = 0; i < matrix.length; i++) {
			if(i % 2 == 0) {
				det += matrix[0][i] * determinant(submatrix(matrix, 0, i));
			} else {
				det -= matrix[0][i] * determinant(submatrix(matrix, 0, i));
			}
		}
		
		return det;
	}
	
	public static int min(int a, int b) {
		if(a >= b) {
			return a;
		}
		return b;
	}
}
