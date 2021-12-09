package LeetCode;

import java.util.*;

/**
 * @FileName: WordLadder.java
 * @Description: 单词接龙
 * @Author: ABCpril
 * @Date: 2021/12/09
 */
public class WordLadder {
    /**
     * remove()方法在ArrayList、LinkedList中的实现都是靠一个个遍历并equals判断
     * 而HashSet、LinkedHashSet底层则是HashMap，找到元素的速度远快于List
     * removeAll(a)方法同理，将使用contains(a.element)方法比较，a为List遍历慢，Set则hash快
     */
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        // beginWord != endWord
//        if (beginWord.equals(endWord)) return 1;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        // wordList不需去重，但remove操作过慢会导致TLE
        Set<String> wordSet = new HashSet<>(wordList);
        int ladderLength = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            ladderLength++;
            // for上一层的节点，拓展下一层节点
            for (int k = 1; k <= size; k++) {
                char[] word = queue.poll().toCharArray();
                // wordList.length <= 5000，用26字母替换beginWord.length <= 10遍历次数更少，260次
                for (int i = 0; i < word.length; i++) {
                    char ori = word[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == ori) {
                            continue;
                        }
                        word[i] = c;
                        String newWord = new String(word);

                        if (wordSet.contains(newWord)) {
                            // endWord需要在词典里
                            if (newWord.equals(endWord)) {
                                return ladderLength;
                            }
                            queue.offer(newWord);
                            // 剪枝，起visited标记作用
                            wordSet.remove(newWord);
                        }
                    }
                    // 恢复现场
                    word[i] = ori;
                }
            }
        }

        return 0;
    }


    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        // wordList不需去重，但remove操作过慢会导致TLE
        Set<String> wordSet = new HashSet<>(wordList);
        int ladderLength = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            // for上一层的节点，拓展下一层节点
            for (int k = 1; k <= size; k++) {
                String curtWord = queue.poll();
                if (curtWord.equals(endWord)) {
                    return ladderLength;
                }
                // 找到所有和curtWord只差1个字母的单词加入队列
                putNextWords(curtWord, wordSet, queue);
            }
            ladderLength++;
        }
        return 0;
    }

    // 找到所有和curtWord只差1个字母的单词加入队列，并从wordSet中删除
    private void putNextWords(String curtWord, Set<String> wordSet, Queue<String> q) {
        // removeAll(a)方法，将使用contains(a.element)方法比较，List类遍历慢，Set类hash快
        Set<String> removableWordsSet = new HashSet<>();
        for (String dictWord : wordSet) {
            int diff = 0;
            // wordList.length <= 5000，用26字母替换beginWord.length <= 10遍历次数更少，260次
            // 以下更适用wordList.length <= 26 * beginWord.length的情况
            for (int i = 0; i < curtWord.length(); i++) {
                if (diff > 1) break;
                if (dictWord.charAt(i) != curtWord.charAt(i)) {
                    ++diff;
                }
            }
            if (diff == 1) {
                q.offer(dictWord);
                // 使用iterator遍历的时候进行修改，会抛出ConcurrentModificationException，所以迭代完再用removeAll删除
                removableWordsSet.add(dictWord);
            }
        }
        // 剪枝，起visited标记作用
        wordSet.removeAll(removableWordsSet);
    }
}
