package com.batinalp.voting_system;

import java.util.ArrayList;
public class Envelope {
    private static final ArrayList<Candidate> candidates = new ArrayList<>();
    private String vote;
    private static int totalVotes;

    public Envelope() {
        totalVotes++;
    }

    public void castVote(String vote) {
        for (int i = 0; i < candidates.size(); i++) {
            if (vote.equals(candidates.get(i).getName())) {
                this.vote = vote;
                candidates.get(i).setTotalVotesForCandidate(candidates.get(i).getTotalVotesForCandidate() + 1);
                break;
            }
        }
    }

    public String getVote() {
        return vote;
    }

    public static void addCandidate(Candidate candidate) {
        candidates.add(candidate);
    }

    public static ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    public static int getTotalVotes() {
        return totalVotes;
    }
}

