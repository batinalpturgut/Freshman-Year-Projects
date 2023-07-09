package com.batinalp.voting_system;

import java.util.ArrayList;
public class BallotBox {
    private final ArrayList<Envelope> envelopes;
    private final int boxNumber;
    private static int totalBoxCount;
    private final int[] votesForCandidateInBox;

    public BallotBox(int boxNumber) {
        envelopes = new ArrayList<>();
        this.boxNumber = boxNumber;
        totalBoxCount = totalBoxCount + 1;
        System.out.println("Creating Ballot Box with number " + totalBoxCount);
        votesForCandidateInBox = new int[Candidate.getTotalCandidateCount()];
    }

    public void countVotesInBox() {
        ArrayList<Candidate> candidates = Envelope.getCandidates();
        System.out.println("Total votes and vote percentages in Box number " + boxNumber + ":");
        double votePercentage;
        for (int i = 0; i < candidates.size(); i++) {

            if (envelopes.size() == 0) {
                votePercentage = 0;
            } else {
                votePercentage = (100d * votesForCandidateInBox[i]) / envelopes.size();
            }

            System.out.println("Vote percentage for candidate " + candidates.get(i).getName() + ": %" + votePercentage);
        }

        System.out.println("Total votes in the box: " + envelopes.size());
        System.out.println();
    }
    public void addEnvelope(Envelope envelope) {
        envelopes.add(envelope);
        System.out.println("Your envelope has been added to Ballot Box number " + boxNumber + "\n");
        for (int i = 0; i < votesForCandidateInBox.length; i++) {
            if (envelope.getVote().equals(Envelope.getCandidates().get(i).getName())) {
                votesForCandidateInBox[i]++;
                break;
            }
        }
    }

    public int getBoxNumber() {
        return boxNumber;
    }
}
