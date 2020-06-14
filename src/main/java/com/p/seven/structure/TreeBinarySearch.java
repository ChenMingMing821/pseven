package com.p.seven.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 二叉查找树：
 * 每棵子树头节点的值都比各自左子树上所有节点值要大，也都比各自右子树上所有节点值要小。
 * 二叉查找树的中序遍历序列一定是从小到大排列的。
 */
public class TreeBinarySearch {
    // 待排序的数字
    public int[] unsortData = {3, 9, 8, 7, 6, 5, 9, 4, 8, 3, 2, 1, 0};

    // 定义二叉查找数的根节点，从而构建一个二叉查找树
    public TreeNode root = null;

    public TreeNode empty = null;

    public TreeBinarySearch() {
        empty = new TreeNode();
        empty.data = -1;
    }


    /**
     * 插入节点的整体流程：
     * <p>
     * 1、把父节点设置为当前节点，即根节点。
     * <p>
     * 2、如果新节点内的数据值小于当前节点内的数据值，那么把当前节点设置为当前节点的左子节点。如果新节点内的数据值大于当前节点内的数据值，那么就跳到步骤 4。
     * <p>
     * 3、如果当前节点的左子节点的数值为空（null），就把新节点插入在这里并且退出循环。否则，跳到 while 循环的下一次循环操作中。
     * <p>
     * 4、把当前节点设置为当前节点的右子节点。
     * <p>
     * 5、如果当前节点的右子节点的数值为空（null），就把新节点插入在这里并且退出循环。否则，跳到 while 循环的下一次循环操作中。
     */
    public void insert(int data) {
        // 构建新的树节点
        TreeNode insertNode = new TreeNode();
        insertNode.data = data;
        // 如果根节点为空，则将新节点设置为根节点
        if (null == root) {
            root = insertNode;
        } else {
            // 新插入的节点需要不断匹配找到合适的位置，这里定义匹配的节点，从根节点开始比较
            TreeNode compareNode = root;

            // 构建二叉查找树比较简单，左叶子小，右叶子大，所以不管比较出来是大还是小，都不需要交换节点位置，而是放到左边或者右边就可以。
            while (true) {
                TreeNode parent = compareNode;
                if (insertNode.data > compareNode.data) {
                    compareNode = compareNode.right;
                    if (null == compareNode) {
                        parent.right = insertNode;
                        break;
                    }
                } else {
                    compareNode = compareNode.left;
                    if (null == compareNode) {
                        parent.left = insertNode;
                        break;
                    }
                }
            }
        }
    }

    /**
     * 前序遍历二叉树。具体过程：
     * 先访问根节点
     * 再序遍历左子树
     * 最后序遍历右子树
     *
     * @param start
     */
    public void preOrder(TreeNode start) {
        if (null == start) {
            return;
        }

        // 1、先遍历当前节点
        start.displayNode();

        // 2、遍历左子树
        preOrder(start.left);

        // 3、遍历右子树
        preOrder(start.right);
    }

    /**
     * 中序遍历。步骤：
     * 先中序遍历左子树
     * 再访问根节点
     * 最后中序遍历右子树
     */
    public void inOrder(TreeNode start) {
        if (null == start) {
            return;
        }

        // 1、遍历左子树
        preOrder(start.left);

        // 2、遍历当前节点
        start.displayNode();

        // 3、遍历右子树
        preOrder(start.right);
    }

    /**
     * 后续遍历
     * 先后序遍历左子树
     * 再后序遍历右子树
     * 最后访问根节点
     */
    public void posOrder(TreeNode start) {
        if (null == start) {
            return;
        }

        // 1、遍历左子树
        preOrder(start.left);

        // 2、遍历右子树
        preOrder(start.right);

        // 3、遍历当前节点
        start.displayNode();
    }

    /**
     * 层序遍历：逐层遍历树。
     * 首先申请一个新的队列，记为queue；
     * 将头结点head压入queue中；
     * 每次从queue中出队，记为node，然后打印node值，如果node左孩子不为空，则将左孩子入队；如果node的右孩子不为空，则将右孩子入队；
     * 重复步骤3，直到queue为空。
     *
     * @param start
     */
    public void levelOrder(TreeNode start) {
        if (null == start) {
            return;
        }

        Queue<TreeNode> queue = new ArrayBlockingQueue<TreeNode>(15);
        queue.add(start);

        // 循环遍历整颗树
        while (!queue.isEmpty()) {
            // 得到当前层级的节点，并输出
            TreeNode node = queue.poll();
            node.displayNode();

            // 将当前节点的左右子节点加入队列中进行遍历
            if (null != node.left) {
                queue.add(node.left);
            }
            if (null != node.right) {
                queue.add(node.right);
            }
        }
    }

    /**
     * 删除节点
     * 相对于前面的操作，二叉查找树的删除节点操作就显得要复杂一些了，因为删除节点会有破坏 BST 正确层次顺序的风险。
     * 我们都知道在二叉查找树中的结点可分为：没有子节点的节点，带有一个子节点的节点 ，带有两个子节点的节点 。
     * <p>
     * 删除叶子节点
     * 删除叶子节点是最简单的事情。 唯一要做的就是把目标节点的父节点的一个子节点设置为空（null）。
     * 查看这个节点的左子节点和右子节点是否为空（null）,都为空（null）说明为叶子节点。
     * 然后检测这个节点是否是根节点。如果是，就把它设置为空（null）。
     * 否则，如果isLeftChild 为true，把父节点的左子节点设置为空（null）；如果isLeftChild 为false,把父节点的右子节点设置为空（null）。
     */
    public void delete(TreeNode node) {
        if (null == node) {
            return;
        }

        // 如果删除节点是叶子节点
        if (null == node.left & null == node.right) {
            if (node.equals(root)) {
                root = null;
            } else {

            }
        }
    }

    /**
     * 找到节点的父亲节点
     * 从根节点的子节点开始查找，因为根节点没有父节点，没有查找意义。
     *
     * @param node
     */
    public TreeNode findParent(TreeNode node, TreeNode parent) {
        if (null == parent) {
            return empty;
        }

        // 1、先遍历当前节点
        if (node == parent.left || node == parent.right) {
            return parent;
        }

        // 2、遍历左子树
        TreeNode result = findParent(node, parent.left);
        if (null != result && empty != result) {
            return result;
        }

        // 3、遍历右子树
        result = findParent(node, parent.right);
        if (null != result && empty != result) {
            return result;
        }

        return empty;
    }

    /**
     * 判断当前节点是否左叶子
     *
     * @param node
     * @return
     */
    public boolean isLeftChild(TreeNode node) {
        TreeNode parent = this.findParent(node, root);
        return (null != parent && node == parent.left) ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * 判断当前叶子是否右叶子
     *
     * @param node
     * @return
     */
    public boolean isRightChild(TreeNode node) {
        TreeNode parent = this.findParent(node, root);
        return (null != parent && node == parent.right) ? Boolean.TRUE : Boolean.FALSE;

    }

    /**
     * 查找节点
     * 对于 二叉查找树（BST） 有三件最容易做的事情：查找一个特殊数值，找到最小值，以及找到最大值。
     *
     * @param type 1 最小值/2 最大值/3 特殊值
     */
    public void search(int type, int key) {
        TreeNode result = empty;
        TreeNode node = root;

        if (null != node) {
            // 查找二叉查找树最小值
            if (1 == type) {
                result = searchMinNode();
            } else if (2 == type) {
                result = searchMaxNode();
            } else {
                result = searchByKey(key);
            }
        }

        if (empty == result) {
            System.out.println("没有在树中找到：" + key);
        } else {
            System.out.println("你要找的是：" + result.data);
        }
    }

    /**
     * 查找节点
     * <p>
     * 查找最小值
     * 根据二叉查找树的性质，二叉查找树的最小值一定是在左子树的最左侧子节点。所以实现很简单，就是从根结点出发找出二叉查找树左子树的最左侧子节点。
     */
    public TreeNode searchMinNode() {
        TreeNode result = empty;
        TreeNode node = root;

        // 查找二叉查找树最小值
        if (null != node) {
            while (null != node.left) {
                node = node.left;
            }
            result = node;
        }
        return result;
    }

    /**
     * 查找节点
     * <p>
     * 查找最大值
     * 根据二叉查找树的性质，二叉查找树的最大值一定是在右子树的最右侧子节点。所以实现很简单，就是从根结点出发找出二叉查找树右子树的最右侧子节点。
     */
    public TreeNode searchMaxNode() {
        TreeNode result = empty;
        TreeNode node = root;

        if (null != node) {
            while (null != node.right) {
                node = node.right;
            }
            result = node;
        }
        return result;
    }

    /**
     * 查找节点
     * <p>
     * 查找特定值
     * 根据二叉查找树的性质，从根结点开始，比较特定值和根结点值的大小。如果比根结点值大，则说明特定值在根结点右子树上，继续在右子节点执行此操作；
     * 如果比根结点值小，则说明特定值在根结点左子树上，继续在左子节点执行此操作。如果到执行完成都没有找到和特定值相等的节点值，那么二叉查找树中没有包含此特定值的节点。
     */
    public TreeNode searchByKey(int key) {
        TreeNode result = empty;
        TreeNode node = root;

        if (null != node) {
            while (true) {
                if (key > node.data) {
                    node = node.right;
                } else if (key < node.data) {
                    node = node.left;
                } else {
                    result = node;
                    break;
                }

                // 已经到达树的末尾
                if (null == node) {
                    break;
                }
            }
        }
        return result;
    }


    public void print(List<TreeNode> current) {
        if (null == current || 0 >= current.size()) {
            return;
        }
        List<TreeNode> next = new ArrayList<TreeNode>();
        for (TreeNode node : current) {
            System.out.print(node.data + " ");
            if (null != node.left) {
                next.add(node.left);
            }
            if (null != node.right) {
                next.add(node.right);
            }
        }
        System.out.println();
        print(next);
    }

    public static void main(String[] args) {
        TreeBinarySearch btree = new TreeBinarySearch();

        // 将待排序数据撸入二叉树
        for (int i = 0; i < btree.unsortData.length; i++) {
            btree.insert(btree.unsortData[i]);
        }

        // 在二叉树中查找值
        btree.search(1, -1);// 最小值
        btree.search(2, -1);// 最大值
        btree.search(3, 6);// 特殊值
        btree.search(3, 16);// 特殊值

        // 前序遍历
        System.out.print("开始前序遍历：");
        btree.preOrder(btree.root);
        System.out.println();

        // 中序遍历
        System.out.print("开始中序遍历：");
        btree.inOrder(btree.root);
        System.out.println();

        // 后序遍历
        System.out.print("开始后序遍历：");
        btree.posOrder(btree.root);
        System.out.println();

        // 层级遍历
        System.out.print("开始层级遍历：");
        btree.levelOrder(btree.root);
        System.out.println();

        TreeNode node = btree.searchByKey(6);
        TreeNode parent = btree.empty;
        parent = btree.findParent(node, btree.root);
        System.out.println("parent node is : " + parent.data);
        System.out.println((btree.isLeftChild(node)) ? node.data + "是左叶子" : node.data + "不是左叶子");

        // 遍历二叉树
        List<TreeNode> current = new ArrayList<TreeNode>();
        System.out.println();
        System.out.println("开始遍历二叉树");
        current.add(btree.root);
        btree.print(current);
    }

}

/**
 * 定义一个二叉查找树节点
 */
class TreeNode {
    // 节点值
    public int data;
    // 左子节点
    public TreeNode left;
    // 右子节点
    public TreeNode right;

    // 打印节点值
    public void displayNode() {
        System.out.print(data + " ");
    }
}