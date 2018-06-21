package piejohnnylikes;

import java.util.ArrayList;
import java.util.List;

import algorithm.hillclimbsearch.HillClimbClassifierSearch;
import piejohnnylikes.Pie.CrustShades;
import piejohnnylikes.Pie.CrustSizes;
import piejohnnylikes.Pie.FillingShades;
import piejohnnylikes.Pie.FillingSizes;
import piejohnnylikes.Pie.Shapes;

public class PiesJohnnyLikes {

	public static void main(String[] args) {

		List<Pie> trainingSet = generateTrainingSet();

		PieClassifier initialClassifier = new PieClassifier(new PieDescriptor(Shapes.CIRCLE));
		PieClassifier finalClassifier = new PieClassifier(0.05);

		HillClimbClassifierSearch<PieClassifier, PieSearchOperator, Double, Boolean, Pie, PieEvaluationFunction> search = new HillClimbClassifierSearch<>(
				new PieEvaluationFunction(), PieSearchOperator.getCombinationOfOperators(),
				new PieSearchOperatorApplier());

		PieClassifier foundClassifier = search.findClassifier(initialClassifier, finalClassifier, trainingSet, 1000);
		System.out.println("found classifier? " + (foundClassifier != null));
		if (foundClassifier != null) {
			System.out.println(foundClassifier);
		}
	}

	private static List<Pie> generateTrainingSet() {
		List<Pie> pies = new ArrayList<>();

		// Johnny likes
		pies.add(new Pie(Shapes.CIRCLE, CrustSizes.Thick, CrustShades.Gray, FillingSizes.Thick, FillingShades.Dark,
				true));
		pies.add(new Pie(Shapes.CIRCLE, CrustSizes.Thick, CrustShades.White, FillingSizes.Thick, FillingShades.Dark,
				true));
		pies.add(new Pie(Shapes.TRIANGLE, CrustSizes.Thick, CrustShades.Dark, FillingSizes.Thick, FillingShades.Gray,
				true));
		pies.add(new Pie(Shapes.CIRCLE, CrustSizes.Thin, CrustShades.White, FillingSizes.Thin, FillingShades.Dark,
				true));
		pies.add(new Pie(Shapes.SQUARE, CrustSizes.Thick, CrustShades.Dark, FillingSizes.Thin, FillingShades.White,
				true));
		pies.add(new Pie(Shapes.CIRCLE, CrustSizes.Thick, CrustShades.White, FillingSizes.Thin, FillingShades.Dark,
				true));

		// Johnny doesn't like
		pies.add(new Pie(Shapes.CIRCLE, CrustSizes.Thick, CrustShades.Gray, FillingSizes.Thick, FillingShades.White,
				false));
		pies.add(new Pie(Shapes.SQUARE, CrustSizes.Thick, CrustShades.White, FillingSizes.Thick, FillingShades.Gray,
				false));
		pies.add(new Pie(Shapes.TRIANGLE, CrustSizes.Thin, CrustShades.Gray, FillingSizes.Thin, FillingShades.Dark,
				false));
		pies.add(new Pie(Shapes.CIRCLE, CrustSizes.Thick, CrustShades.Dark, FillingSizes.Thick, FillingShades.White,
				false));
		pies.add(new Pie(Shapes.SQUARE, CrustSizes.Thick, CrustShades.White, FillingSizes.Thick, FillingShades.Dark,
				false));
		pies.add(new Pie(Shapes.TRIANGLE, CrustSizes.Thick, CrustShades.White, FillingSizes.Thick, FillingShades.Gray,
				false));

		return pies;
	}

}
