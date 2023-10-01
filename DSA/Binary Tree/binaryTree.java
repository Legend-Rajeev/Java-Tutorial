import java.util.LinkedList;
import java.util.Queue;

import javax.management.Query;

class BinaryTree{

    static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTreeYT{
        public static int idx = -1;
        public static Node buildTree(int nodes[]){
            idx++;
            if(nodes[idx] == -1){
                return null;
            }
            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }

        public static void preorder(Node root){
            if(root == null)
                return;
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);

        }

        public static void inorder(Node root){
            if(root == null)
                return;

            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);

        }

        public static void postorder(Node root){
            if(root == null)
                return;
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }

        public static void levelorder(Node root){
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);

            while(!q.isEmpty()){
                Node currNode = q.remove();
                if(currNode == null){
                    if(q.isEmpty()){
                        break;
                    }else{
                        System.out.println();
                        q.add(null);
                    }
                }else{
                    if(currNode.left != null){
                        q.add(currNode.left);
                    }

                    if(currNode.right != null){
                        q.add(currNode.right);
                    }
                    System.out.print(currNode.data + " ");
                }
            }

        }
        
        public static int countOfNodes(Node root){
            if(root == null){
                return 0;
            }
            
            int x = countOfNodes(root.left);
            int y = countOfNodes(root.right);
            return x + y + 1;
            
        }

        public static int sumOfNodes(Node root){
            if(root == null)
                return 0;
            int x = sumOfNodes(root.left);
            int y = sumOfNodes(root.right);
            return x + y + root.data;

        }

        public static int heightOfTree(Node root){
            if(root == null)
                return 0;

            int x = heightOfTree(root.left);
            int y = heightOfTree(root.right);
            return Math.max(x, y) + 1;
        }

        public static int diameter(Node root){
            if(root == null)
                return 0;
            
            int diam1 = diameter(root.left);
            int diam2 = diameter(root.right);
            int diam3 = heightOfTree(root.left) + heightOfTree(root.right) + 1;

            return Math.max(diam3, Math.max(diam1, diam2));
        }
    }

    static class TreeInfo{
        int ht;
        int diam;

        TreeInfo(int ht, int diam){
            this.ht = ht;
            this.diam = diam;
        }
    }

    public static TreeInfo diameter2(Node root){
        if(root == null){
            return new TreeInfo(0, 0);
        }
        TreeInfo left = diameter2(root.left);
        TreeInfo right = diameter2(root.right);

        int diam1 = left.diam;
        int diam2 = right.diam;
        int diam3 = left.ht + right.ht + 1;

        int mydiam = Math.max(diam3, Math.max(diam2, diam1));
        int myheight = Math.max(left.ht, right.ht) + 1;
        return new TreeInfo(myheight, mydiam);
    }

    public static boolean isIdentical(Node root, Node subroot){
        if(root == null && subroot == null){
            return true;
        }
        if(root==null || subroot == null){
            return false;
        }
        return isIdentical(root.left, subroot.left) && isIdentical(root.right, subroot.right);
    }

    public static boolean isSubtree(Node root, Node subroot){
        if(subroot == null){
            return true;
        }
        if(root == null){
            return false;
        }
        if(root.data == subroot.data){
            if(isIdentical(root, subroot)){
                return true;
            }
        }
        
        return isSubtree(root.left, subroot) || isSubtree(root.right, subroot);
    }

    public static void main(String args[]){
        System.out.println("Binary Tree ");

        int nodes[] = {9, 7, 8, -1, -1, 1, 2, -1, -1, 3, -1, -1, 4, 5, -1, -1, 6, -1, -1};
        BinaryTreeYT bt = new BinaryTreeYT();
        Node root = bt.buildTree(nodes);
        bt.preorder(root);
        System.out.println();
        bt.inorder(root);
        System.out.println();
        bt.postorder(root);
        System.out.println();
        bt.levelorder(root);
        System.out.println("\nNumber of Nodes -> " + bt.countOfNodes(root));
        System.out.println("Sum of Nodes -> " + bt.sumOfNodes(root));
        System.out.println("Sum of Nodes -> " + bt.heightOfTree(root));
        System.out.println("Diameter -> " + bt.diameter(root));
        System.out.println("Diameter2 -> " + diameter2(root).diam);

        BinaryTreeYT bttt1 = new BinaryTreeYT();
        bttt1.idx = -1;
        int subnodes[] = {1, 2, -1, -1, 3, -1, -1};

        Node subroot = bttt1.buildTree(subnodes);
        bt.preorder(subroot);
        System.out.println(" Is Subtree -> " + isSubtree(root, subroot));
    }
}