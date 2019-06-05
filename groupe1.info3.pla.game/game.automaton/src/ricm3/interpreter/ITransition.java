package ricm3.interpreter;

import edu.ricm3.game.purgatoire.Controller;
import edu.ricm3.game.purgatoire.Entity;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class ITransition {
	ICondition condition ;
	IAction action ;
	IState target ;
	
	public ITransition(ICondition condition, IAction action, IState target){
		this.condition = condition ;
		this.action = action ;
		this.target = target ;
	}
	
	boolean feasible(Entity e, Controller controller) {
		return condition.eval(e, controller);
	}
	
	IState exec(Entity e) {
		action.exec(e);
		return target;
	}
}
