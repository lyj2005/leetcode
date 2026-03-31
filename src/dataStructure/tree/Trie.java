package dataStructure.tree;

class Trie {
    private TrieNode root;

    // Trie树的节点结构
    class TrieNode {
        // 子节点，使用数组实现（假设只包含小写字母a-z）
        private TrieNode[] children;
        // 标记该节点是否为某个单词的结尾
        private boolean isEndOfWord;

        public TrieNode() {
            children = new TrieNode[26]; // 26个英文字母
            isEndOfWord = false;
        }
    }

    /** 初始化Trie树 */
    public Trie() {
        root = new TrieNode();
    }
    
    /** 向Trie树中插入单词 */
    public void insert(String word) {
        TrieNode current = root;
        
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a'; // 将字符转换为索引
            
            // 如果当前字符的节点不存在，创建一个新节点
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            
            // 移动到下一个节点
            current = current.children[index];
        }
        
        // 标记单词结束
        current.isEndOfWord = true;
    }
    
    /** 查找Trie树中是否存在完整单词 */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        
        // 节点存在且是单词结尾
        return node != null && node.isEndOfWord;
    }
    
    /** 查找Trie树中是否存在指定前缀 */
    public boolean startsWith(String prefix) {
        // 只需要节点存在即可
        return searchPrefix(prefix) != null;
    }
    
    /** 查找前缀对应的节点 */
    private TrieNode searchPrefix(String prefix) {
        TrieNode current = root;
        
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            
            // 如果当前字符的节点不存在，返回null
            if (current.children[index] == null) {
                return null;
            }
            
            current = current.children[index];
        }
        
        return current;
    }
    
    /** 从Trie树中删除单词（较复杂的操作） */
    public void delete(String word) {
        delete(root, word, 0);
    }
    
    private boolean delete(TrieNode current, String word, int index) {
        // 已经处理完所有字符
        if (index == word.length()) {
            // 如果不是单词结尾，单词不存在
            if (!current.isEndOfWord) {
                return false;
            }
            
            // 取消标记单词结尾
            current.isEndOfWord = false;
            
            // 如果节点没有子节点，可以删除
            return hasNoChildren(current);
        }
        
        char ch = word.charAt(index);
        int childIndex = ch - 'a';
        TrieNode child = current.children[childIndex];
        
        // 如果字符对应的节点不存在，单词不存在
        if (child == null) {
            return false;
        }
        
        // 递归删除子节点
        boolean shouldDeleteChild = delete(child, word, index + 1);
        
        // 如果子节点应该被删除
        if (shouldDeleteChild) {
            current.children[childIndex] = null;
            
            // 如果当前节点不是单词结尾且没有其他子节点，则它也可以被删除
            return !current.isEndOfWord && hasNoChildren(current);
        }
        
        return false;
    }
    
    private boolean hasNoChildren(TrieNode node) {
        for (TrieNode child : node.children) {
            if (child != null) {
                return false;
            }
        }
        return true;
    }
}
