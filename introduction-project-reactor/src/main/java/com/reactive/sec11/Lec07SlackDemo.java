package com.reactive.sec11;

import com.reactive.sec11.assignment.SlackMember;
import com.reactive.sec11.assignment.SlackRoom;

import static com.reactive.utils.ReactiveUtility.sleepMillis;
import static com.reactive.utils.ReactiveUtility.sleepSeconds;

public class Lec07SlackDemo {

  public static void main(final String[] args) {
    final SlackRoom slackRoom = new SlackRoom("reactor");
    final SlackMember sam = new SlackMember("sam");
    final SlackMember jake = new SlackMember("jake");
    final SlackMember mike = new SlackMember("mike");

    slackRoom.joinRoom(sam);
    slackRoom.joinRoom(jake);

    sam.says("Hi all..");
    sleepSeconds(4);
    jake.says("Hey!");
    sleepMillis(500);
    sam.says("I simply wanted to say hi..");
    sleepMillis(500);
    slackRoom.joinRoom(mike);
    sleepMillis(500);
    mike.says("Hey guys!.. glad to be here...");

  }
}
