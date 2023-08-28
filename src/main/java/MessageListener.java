import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.Collections;
import java.util.List;

public class MessageListener extends ListenerAdapter
{
    public static void main(String[] args)
    {
        if(args.length < 1) {
            System.out.println("CAN NOT RUN WITHOUT TOKEN, APP FAILURE");
            System.exit(1);
        }

        JDA jda = JDABuilder.createDefault(args[0])
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new MessageListener())
                .setActivity(Activity.playing("Type /ping"))
                .build();

        // Sets the global command list to the provided commands (removing all others)
        jda.updateCommands().addCommands(
                Commands.slash("내전", "내전 게임 인원을 모집합니다.")
                        .addOption(OptionType.STRING, "가나다", "라마바", true),
                Commands.slash("일반", "일반 게임 인원을 모집합니다."),
                Commands.slash("자랭", "자랭 게임 인원을 모집합니다.")
                        .setGuildOnly(true) // Ban command only works inside a guild
        ).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event)
    {
        System.out.println("ENENT NAME :: " + event.getName());
        System.out.println(String.format("%s - %s", event.getFullCommandName(), event.getCommandString()));
        // make sure we handle the right command
        switch (event.getName()) {
            case "내전": {
                var time = System.currentTimeMillis();
                event.reply("""
                                🌈 내전 (닉네임, 티어) 🌈
                                시작시간 : 
                                1. 
                                2.
                                3.
                                4.
                                5.
                                6.
                                7.
                                8.
                                9.
                                10.
                                                        
                                대기 : 
                                """).setEphemeral(true) // reply or acknowledge
                .queue(); // Queue both reply and edit
                break;
            }
            case "일반": {
                var time = System.currentTimeMillis();
                event.reply("""
                                🌈 일반게임 (닉네임, 티어) 🌈
                                시작시간 : 
                                1. 
                                2.
                                3.
                                4.
                                5.
                                                        
                                대기 : 
                                """).setEphemeral(true) // reply or acknowledge
                    .queue(); // Queue both reply and edit
                break;
            }
            case "자랭" : {
                var time = System.currentTimeMillis();
                event.reply("""
                                🌈 자유랭크 (닉네임, 티어) 🌈
                                시작시간 : 
                                1. 
                                2.
                                3.
                                4.
                                5.
                                                        
                                대기 : 
                                """).setEphemeral(true) // reply or acknowledge
                        .queue(); // Queue both reply and edit
                break;
            }
        }
    }
}