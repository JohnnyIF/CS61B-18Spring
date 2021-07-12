package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;



/**
 * @Author： Hengyi
 * @Date 2021-07-11 17:31
 * @Version 1.0
 */
public class Solver {

    private class SearchNode {
        private WorldState state;
        private SearchNode prev;
        private int moves;
        SearchNode(WorldState state, SearchNode prev, int moves) {
            this.state = state;
            this.prev = prev;
            this.moves = moves;
        }
    }

    private class NodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode n1, SearchNode n2) {
            return n1.moves +  getDisToG(n1) - n2.moves - getDisToG(n2);
        }

        private int getDisToG(SearchNode sn) {
            if (!cachesDistance.containsKey(sn.state)) {
                cachesDistance.put(sn.state, sn.state.estimatedDistanceToGoal());
            }
            return cachesDistance.get(sn.state);
        }
    }
    private List<WorldState> solutions = new ArrayList<>();
    private Map<WorldState, Integer> cachesDistance = new HashMap<>();

    public Solver(WorldState initial) {
        MinPQ<SearchNode> pq = new MinPQ<>(new NodeComparator());
        SearchNode currentNode = new SearchNode(initial, null, 0);
        pq.insert(currentNode);

        while (!pq.isEmpty()) {
            currentNode = pq.delMin();
            if (currentNode.state.isGoal()) {
                break;
            }

            for (WorldState nextState : currentNode.state.neighbors()) {
                SearchNode newNode = new SearchNode(nextState, currentNode, currentNode.moves + 1);
                // A critical optimization checks that no enqueued WorldState is its own
                // grandparent
                if (currentNode.prev != null && nextState.equals(currentNode.prev.state)) {
                    continue;
                }
                pq.insert(newNode);
            }
        }

        Stack<WorldState> path = new Stack<>();
        for (SearchNode n = currentNode; n != null; n = n.prev) {
            path.push(n.state);
        }
        while (!path.isEmpty()) {
            solutions.add(path.pop());
        }



    }
    public int moves() {
        return solutions.size() - 1;
    }
    public Iterable<WorldState> solution() {
        return solutions;
    }
}
