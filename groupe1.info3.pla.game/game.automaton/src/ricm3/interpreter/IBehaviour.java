package ricm3.interpreter;
import java.util.Iterator;
import java.util.List;

import edu.ricm3.game.purgatoire.Controller;
import edu.ricm3.game.purgatoire.Entity;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IBehaviour {
	IState source ;
	List<ITransition> transitions ;
	
	public IBehaviour(IState source, List<ITransition> transitions){
		this.source = source ; 
		this.transitions = transitions ;
	}
	
	IState step(Entity e, Controller controller) throws NoFeasibleTransition {
		Iterator<ITransition> iter = transitions.iterator();
		while(iter.hasNext()) {
			ITransition transition = iter.next();
			if(transition.feasible(e, controller)) {
				transition.exec(e);
				return transition.target;
			}
		}
		throw new NoFeasibleTransition();
	}
}
