import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestButtonPress.class,
	TestCalcModel.class,
	TestCalcController.class,
	TestCalcView.class,
	TestDrawFunction.class,
	TestCalcFavourites.class
})
public class AllTest {

}
