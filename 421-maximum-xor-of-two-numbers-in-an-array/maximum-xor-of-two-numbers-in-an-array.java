class Solution {

    class TrieNode {
        TrieNode[] child = new TrieNode[2];
    }

    private TrieNode root = new TrieNode();

    private void insert(int num) {
        TrieNode node = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (node.child[bit] == null) {
                node.child[bit] = new TrieNode();
            }
            node = node.child[bit];
        }
    }

    private int getMaxXor(int num) {
        TrieNode node = root;
        int xor = 0;

        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            int opposite = 1 - bit;

            if (node.child[opposite] != null) {
                xor |= (1 << i);
                node = node.child[opposite];
            } else {
                node = node.child[bit];
            }
        }

        return xor;
    }

    public int findMaximumXOR(int[] nums) {
        for (int num : nums) {
            insert(num);
        }

        int max = 0;
        for (int num : nums) {
            max = Math.max(max, getMaxXor(num));
        }

        return max;
    }
}