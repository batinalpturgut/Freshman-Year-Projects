package com.batinalp.voting_system;

import java.util.ArrayList;
import java.util.Scanner;

public class Voting {

    GeneralElection generalElection;
    private int candidateCount;
    private int ballotBoxCount;
    private ArrayList<Candidate> candidates;
    private ArrayList<BallotBox> ballotBoxes;

    public Voting() {
        generalElection = new GeneralElection();
        addCandidatesToBallot();
    }

    public void addCandidatesToBallot() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the number of candidates participating in the voting: ");
        candidateCount = scan.nextInt();
        scan.nextLine();

        while (candidateCount <= 1) {
            System.out.println("Invalid value. Try again");
            System.out.println("Please enter the number of candidates participating in the voting: ");
            candidateCount = scan.nextInt();
            scan.nextLine();
        }
        for (int i = 0; i < candidateCount; i++) {
            System.out.println("Enter the name of candidate " + (i + 1) + ":");
            String candidateName = scan.nextLine();
            Envelope.addCandidate(new Candidate(candidateName));
        }

        addBallotBoxesToGeneralElection();
    }

    public void addBallotBoxesToGeneralElection() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of ballot boxes to be used in the voting: ");
        ballotBoxCount = scan.nextInt();
        while(ballotBoxCount < 1){
            System.out.println("Invalid value. Try again.");
            System.out.println("Enter the number of ballot boxes to be used in the voting: ");
            ballotBoxCount = scan.nextInt();
        }
        for (int i = 0; i < ballotBoxCount; i++) {
            generalElection.addBallotBox(new BallotBox(i + 1));
        }

        takingBallotBoxesAndCandidates();
    }

    public void takingBallotBoxesAndCandidates() {
        candidates = Envelope.getCandidates();
        ballotBoxes = generalElection.getBallotBoxes();
        electionIsStarted();
    }

    public void electionIsStarted() {
        do {
            int vote = chooseCandidateToVote();
            Envelope e = new Envelope();
            e.castVote(candidates.get(vote).getName());
            int ballotBoxToVoteIn = whichBallotBox() - 1;
            ballotBoxes.get(ballotBoxToVoteIn).addEnvelope(e);
            ballotBoxes.get(ballotBoxToVoteIn).countVotesInBox();
            generalElection.displayElectionInformation();
        } while (isElectionOver());

        generalElection.displayAllBoxInformation();
        generalElection.displayElectionResults();
    }


    public int whichBallotBox() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Which ballot box will you vote in ?");
        int ballotBoxNumber = scan.nextInt();
        while (ballotBoxNumber < 1 || ballotBoxNumber > ballotBoxCount) {
            System.out.println("Such a ballot box does not exist. Please try again.");
            System.out.println("Which ballot box will you vote in ?");
            ballotBoxNumber = scan.nextInt();
            scan.nextLine();
        }
        scan.nextLine();
        return ballotBoxNumber;
    }

    public int chooseCandidateToVote() {
        Scanner scan = new Scanner(System.in);
        showCandidates();
        System.out.println("Which candidate are you voting for ?");
        int vote = scan.nextInt() - 1;
        while (vote < 0 || vote > candidateCount - 1) {
            System.out.println("Invalid input. Try again.");
            System.out.println("Which candidate are you voting for ?");
            vote = scan.nextInt() - 1;
        }
        return vote;
    }


    public void showCandidates() {
        for (int i = 0; i < candidateCount; i++) {
            System.out.println(i + 1 + "-" + candidates.get(i).getName());
        }
    }

    private boolean isElectionOver() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Is the election still in progress?\n1- Yes\n0- No");
        int num = scan.nextInt();
        while (num != 1 && num != 0) {
            System.out.println("Invalid input. Try again");
            System.out.println("Is the election still in progress?\n1- Yes\n0- No");
            num = scan.nextInt();
        }
        return num == 1;
    }
}
