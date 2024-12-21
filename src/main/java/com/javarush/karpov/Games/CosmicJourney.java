package com.javarush.karpov.Games;

import com.javarush.karpov.entity.*;

import java.util.List;

public class CosmicJourney extends Quest {

    public CosmicJourney() {
        super("Cosmic Journey", 1, List.of(
                QuestStage.builder()
                        .title("Пролог")
                        .state(State.PLAYING)
                        .id(0)
                        .image("img/CosmicJourney/stage0.png")
                        .text("""
                                Ты стоишь в космическом порту и готов подняться на борт своего корабля.
                                Разве ты не об этом мечтал?
                                Стать капитаном галактического судна с экипажем, который будет совершать подвиги под твоим командованием.
                                Так что вперед!""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 1))
                        .button("Начать приключение")
                        .build(),
                QuestStage.builder()
                        .title("Знакомство с экипажем")
                        .state(State.PLAYING)
                        .id(1)
                        .image("img/CosmicJourney/stage1.png")
                        .text("""
                                Когда ты поднялся на борт корабля, тебя поприветствовала девушка с черной папкой в руках:
                                - Здравствуйте командир. Я Зинаида - ваша помощница. Видите? Там в углу пьет кофе
                                наш штурман - сержант Перегарный Штейф, под штурвалом спит наш бортмеханик - Черный Богдан
                                а фотографирует его Сергей Стальная Пятка - наш навигатор.
                                А как обращаться к вам?""")
                        .selector(Selector.INPUT)
                        .defaultOption(new Choice("Default Choice", 2))
                        .button("Представиться")
                        .build(),
                QuestStage.builder()
                        .title("В путь")
                        .state(State.PLAYING)
                        .id(2)
                        .image("img/CosmicJourney/stage2.png")
                        .text("""
                                После знакомства с экипажем корабль набрал высоту и вы вышли на крейсерскую скорость.
                                Внезапно Зинаида вскрикнула: Командир, на радарах НЛО! Оно движется прямо на нас!
                                Ты лишь успел увидеть яркую вспышку на экране, прежде чем все погрузилось во тьму.""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 3))
                        .button("Далее")
                        .build(),
                QuestStage.builder()
                        .title("Сложное решение")
                        .state(State.PLAYING)
                        .id(3)
                        .image("img/CosmicJourney/stage3.png")
                        .text("""
                                Ты потерял память. Очнувшись в капитанском кресле, ты ощутил странную тяжесть в голове.
                                "Командир, вы в порядке?" – спросила Зинаида, но её голос звучал будто издалека.
                                Перед тобой на экране висело сообщение от НЛО: "Сотрудничество или конфликт. Решайте быстро.
                                Штурман Штейф нахмурился: Они ждут ответа, командир. Что будем делать?""")
                        .option1(new Choice("Принять вызов НЛО", 4))
                        .option2(new Choice("Отклонить вызов НЛО", 301))
                        .selector(Selector.OPTIONS)
                        .button("Ответить")
                        .build(),
                QuestStage.builder()
                        .title("На чужом корабле")
                        .state(State.PLAYING)
                        .id(4)
                        .image("img/CosmicJourney/stage4.png")
                        .text("""
                                Ты принял вызов.
                                Резкий свет охватил кабину, и ты почувствовал, как воздух вокруг сгустился и через мгновение ты уже стоял в огромном зале, полном сияющих труб и сложных приборов.
                                Из глубины зала послышался голос: "Приветствуем вас, капитан."
                                В полумраке ты заметил высокий силуэт, который двигался к тебе.
                                "Мы наблюдали за вами и вашим экипажем. Поднимитесь на мостик к нашему капитану.\"""")
                        .option1(new Choice("Подняться на мостик к капитану", 5))
                        .option2(new Choice("Отказаться подниматься на мостик", 401))
                        .selector(Selector.OPTIONS)
                        .button("Выбрать")
                        .build(),
                QuestStage.builder()
                        .title("Лицом к лицу")
                        .state(State.PLAYING)
                        .id(5)
                        .image("img/CosmicJourney/stage5.png")
                        .text("""
                                Ты поднялся на мостик.
                                Это оказалась комната похожая на гигантский купол. На стенах светились символы, похожие на древние письмена, которые двигались, словно живые.
                                В центре стоял высокий трон из странного материала, мерцающий, будто бы сделанный из самой тьмы.
                                На троне сидела фигура, закутанная в плащ. Её лицо оставалось скрытым, но два ярких глаза горели из-под капюшона.
                                Внезапно в твоей голове раздался вопрос:
                                "Кто ты такой?\"""")
                        .option1(new Choice("Рассказать правду о себе", 6))
                        .option2(new Choice("Солгать о себе", 501))
                        .selector(Selector.OPTIONS)
                        .button("Ответить")
                                .build(),
                QuestStage.builder()
                        .title("Победа!")
                        .state(State.WON)
                        .id(6)
                        .image("img/CosmicJourney/stage6.png")
                        .text("""
                                Рассказав правду о себе, ты смог договориться с пришельцами.
                                Тебя и твою команду вернули домой""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 0))
                        .button("Пройти заново")
                        .build(),
                QuestStage.builder()
                        .title("Поражение!")
                        .state(State.LOST)
                        .id(301)
                        .image("img/CosmicJourney/stage3-defeat.png")
                        .text("""
                                Ты отклонил вызов. Ваш корабль был уничтожен""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 0))
                        .button("Пройти заново")
                        .build(),
                QuestStage.builder()
                        .title("Поражение!")
                        .state(State.LOST)
                        .id(401)
                        .image("img/CosmicJourney/stage4-defeat.png")
                        .text("""
                                Ты отказался подниматься на мостик. Тебя и твою команду расщепили на атомы""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 0))
                        .button("Пройти заново")
                        .build(),
                QuestStage.builder()
                        .title("Поражение!")
                        .state(State.LOST)
                        .id(501)
                        .image("img/CosmicJourney/stage5-defeat.png")
                        .text("""
                                Твою ложь разоблачили. Ты и твоя команда стали подопытными пришельцев.""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 0))
                        .button("Пройти заново")
                        .build()
        ));
    }
}