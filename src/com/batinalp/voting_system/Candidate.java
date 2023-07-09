package com.batinalp.voting_system;
public class Candidate {
    private String name;
    private int totalVotesForCandidate;
    private static int totalCandidateCount;

    public Candidate(String name) {
        this.name = name;
        totalCandidateCount++;
    }

    public String getName() {
        return name;
    }

    public int getTotalVotesForCandidate() {
        return totalVotesForCandidate;
    }

    public void setTotalVotesForCandidate(int totalVotesForCandidate) {
        this.totalVotesForCandidate = totalVotesForCandidate;
    }

    public static int getTotalCandidateCount() {
        return totalCandidateCount;
    }

    public void setName(String name) {
        this.name = name;
    }
}