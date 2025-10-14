/*
Tristan Shi
October 14, 2025
CS 102 DSA
 */
package assignments.assignment4;

import assignments.assignment4.dependencies.*;

public class AddRemoveLinkedTree<E> extends LinkedTree<E> {


    public AddRemoveLinkedTree(E root_element) {
        super(root_element);
    }

    //add child by creating a new node and adding this node to the children of the parent
    public void addChild(Position<E> p, E element) {
        Node<E> parentNode = validate_position(p);
        Node<E> childNode = new Node<>(element, parentNode);
        parentNode.getChildren().addLast(childNode);
        size++;
    }
    public E removePosition(Position<E> p) {
        Node<E> current = validate_position(p);
        Node<E> parent = current.getParent();
        //when root
        if (parent == null) {
            return null;
        }
        // transfer all children to parent
        while (current.getChildren().size() > 0) {
            //idk why but there is only getAtIndex coded so this is basically getFirst
            Position<E> temp = current.getChildren().getAtIndex(0);
            //set the parent of this position to the parentNode
            validate_position(temp).setParent(parent);
            //add this position to the children of the parentNode
            parent.getChildren().addLast(temp);
            //remove this position from the children list
            current.getChildren().removeAtIndex(0);
        }

        // remove current from its parent's children list
        PositionList<Position<E>> siblings = parent.getChildren();
        for (int i = 0; i < siblings.size(); i++) {
            // identity check
            if (siblings.getAtIndex(i) == current) {
                siblings.removeAtIndex(i);
                break;
            }
        }
        //increment size since there is one less position
        size--;

        return current.getElement();
    }

    AddRemoveLinkedTree<E> getSubtree(Position<E> p) {
        //root node is current position
        Node<E> rootNode = validate_position(p);
        //create a new linked tree with the value of p as the root
        AddRemoveLinkedTree<E> subtree = new AddRemoveLinkedTree<>(rootNode.getElement());
        //recursive method
        copySubtreeWithAddChild(rootNode, subtree, subtree.root);
        return subtree;
    }
//using dfs
    private void copySubtreeWithAddChild(Node<E> node, AddRemoveLinkedTree<E> tree, Node<E> newParent) {
        for (int i = 0; i < node.getChildren().size(); i++) {
            Position<E> childPos = node.getChildren().getAtIndex(i);
            //for each child, add it to the new subtree
            tree.addChild(newParent, childPos.getElement());
            PositionList<Position<E>> children = tree.validate_position(newParent).getChildren();
            Node<E> newChildNode = tree.validate_position(children.getAtIndex(children.size() - 1));
            copySubtreeWithAddChild((Node<E>) childPos, tree, newChildNode);
        }
    }

    //add subtree tree to the original tree at root p
    public void addSubtree(Position<E> p, AddRemoveLinkedTree<E> tree){
        Node<E> parentNode = validate_position(p);
        tree.root.setParent(parentNode);
        parentNode.getChildren().addLast(tree.root);
        size += tree.size;
    }

    public AddRemoveLinkedTree<E> removeSubtree(Position<E> p) {
        AddRemoveLinkedTree<E> result = getSubtree(p);

        Node<E> nodeToRemove = validate_position(p);
        Node<E> parent = nodeToRemove.getParent();
        if (parent != null) {
            //remove the subtree at position p from the addremovelinkedtree
            PositionList<Position<E>> siblings = parent.getChildren();
            for (int i = 0; i < siblings.size(); i++) {
                //remove this subtree from its parent
                if (siblings.getAtIndex(i) == nodeToRemove) {
                    siblings.removeAtIndex(i);
                    break;
                }
            }
            // adjust size=
            size -= result.size();
            //clear the subtree's parent so it is no longer linked
            nodeToRemove.setParent(null);
        } else {
            // p is the root - clear whole tree
            root = null;
            size = 0;
        }
        return result;
    }

    public static <E> void positionsToStringPrint(PositionList<Position<E>> positions) {
        //this method exists so i dont have 15 million print statements
        StringBuilder sb = new StringBuilder();
        for (Position<E> p : positions) {
            sb.append(p.getElement());
            sb.append(" ");
        }
        System.out.println(sb);
    }


    public static void main(String[] args) {
        AddRemoveLinkedTree<String> test = new AddRemoveLinkedTree<>("Root");
        //add alphabet using ascii :)
        for (int i = 65; i <= 90; i++) {
            test.addChild(test.root, (char) i + "");
        }
        //add letter+number concatenation
        for (int i = 0; i < 26; i++) {
            Position<String> current = test.root.getChildren().getAtIndex(i);
            for (int j = 1; j <= 10; j++) {
                String jString = Integer.toString(j);
                test.addChild(current, current.getElement() + jString);
            }
        }
        //tests
        System.out.print("Starting Positions: ");
        positionsToStringPrint(test.positions());
      test.removePosition(test.findPosition("F"));

      //note how the f children become direct children of the root
        System.out.print("After removing the F position: ");
        positionsToStringPrint(test.positions());
        AddRemoveLinkedTree<String> L_Positions =  test.removeSubtree(test.findPosition("L"));
        test.addSubtree(test.validate_position(test.findPosition("C")).getChildren().getAtIndex(4),L_Positions);
        System.out.print("After removing the L node and all its children: ");
        positionsToStringPrint(test.positions());
        System.out.print("The L subtree: ");
        positionsToStringPrint(L_Positions.positions());


    }



}


