package com.batinalp.voting_system;

import java.util.ArrayList;
public class GeneralElection {
    private final ArrayList<BallotBox> ballotBoxes;
    ArrayList<Candidate> candidates;

    public GeneralElection() {
        ballotBoxes = new ArrayList<>();
        candidates = Envelope.getCandidates();
    }

    public void displayAllBoxInformation() {
        for (int i = 0; i < ballotBoxes.size(); i++) {
            ballotBoxes.get(i).countVotesInBox();
        }
        System.out.println();
    }

    public void displayElectionResults() {
        for (int i = 0; i < candidates.size() - 1; i++) {
            for (int j = 0; j < candidates.size() - i - 1; j++) {
                if (candidates.get(j).getTotalVotesForCandidate() < candidates.get(j + 1).getTotalVotesForCandidate()) {
                    Candidate temp = candidates.get(j);
                    candidates.set(j, candidates.get(j + 1));
                    candidates.set(j + 1, temp);
                }
            }
        }
        int candidateRank = 1;
        for (int i = 0; i < candidates.size(); i++) {

            if (i > 0 && candidates.get(i).getTotalVotesForCandidate() != candidates.get(i - 1).getTotalVotesForCandidate()) {
                candidateRank++;
            }

            System.out.println(candidates.get(i).getName() + " has ranked " + candidateRank + " with " +
                    candidates.get(i).getTotalVotesForCandidate() + " votes.");

        }

        if (candidates.get(0).getTotalVotesForCandidate() == candidates.get(1).getTotalVotesForCandidate()) {
            System.out.println("The election will proceed to the second round.");
        } else
            System.out.println("Congratulations!!! " + candidates.get(0).getName() + " has won the election.");

    }

    public void displayElectionInformation() {
        System.out.println("General Election Information: \n");

        int candidateCount = candidates.size();
        for (int i = 0; i < candidateCount; i++) {
            System.out.println("Vote percentage for candidate " + candidates.get(i).getName() + ": %" +
                    (100d * candidates.get(i).getTotalVotesForCandidate()) / Envelope.getTotalVotes()
            );
        }
        System.out.println("Total number of votes: " + Envelope.getTotalVotes());
        System.out.println();

    }

    public void addBallotBox(BallotBox ballotBox) {
        System.out.println("Ballot Box with number " + ballotBox.getBoxNumber() + " has been added to the election.");
        ballotBoxes.add(ballotBox);
        System.out.println();
    }

    public ArrayList<BallotBox> getBallotBoxes() {
        return ballotBoxes;
    }
}
