package com.javarush.karpov.Games;

import com.javarush.karpov.entity.*;

import java.util.List;

public class LastWish extends Quest {

    public LastWish() {
        super("LastWish", 3, List.of(
                QuestStage.builder()
                        .title("Пролог")
                        .state(State.PLAYING)
                        .id(0)
                        .image("img/LastWish/stage0.png")
                        .text("""
                                Ведьмак Геральт и его друг Лютик, отдыхая после напряженных приключений, решают провести день на рыбалке в тихом озере недалеко от города.
                                Геральт, погруженный в размышления, закинул удочку в воду, а Лютик, валяясь неподалеку, рассказывал очередную шутку.""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 1))
                        .button("Далее")
                        .build(),
                QuestStage.builder()
                        .title("")
                        .state(State.PLAYING)
                        .id(1)
                        .image("img/LastWish/stage1.png")
                        .text("""
                                Внезапно, Геральт заметил странную бутылку, плавающую на поверхности воды.
                                Закинув удочку, Геральт сумел подцепить бутылку и подтянуть ее к себе.""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 2))
                        .button("Далее")
                        .build(),
                QuestStage.builder()
                        .title("Находка")
                        .state(State.PLAYING)
                        .id(2)
                        .image("img/LastWish/stage2.png")
                        .text("""
                                Геральт, слегка озадаченный, внимательнее присмотрелся к бутылке.
                                Она была старой, с потрескавшимся стеклом, и на ней был вырезан странный символ. Лютик, заметив необычную находку, воскликнул:
                                — Ого, что за чудо! Может, внутри сокровища? Или... ну, может, еще что-то?
                                Геральт, с его привычным скепсисом, взял бутылку в руки.
                                Она была удивительно легкой. Внутри, несмотря на мутную воду, что-то поблескивало.""")
                        .option1(new Choice("Открыть бутылку, чтобы узнать, что внутри", 3))
                        .option2(new Choice("Не открывать бутылку", 201))
                        .selector(Selector.OPTIONS)
                        .button("Выбрать")
                        .build(),
                QuestStage.builder()
                        .title("Поражение!")
                        .state(State.LOST)
                        .id(201)
                        .image("img/LastWish/stage201.png")
                        .text("""
                                Вы выкинули бутылку обратно в озеро. На этом приключение заканчивается и вы не получаете награду.""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 0))
                        .button("Пройти заново")
                        .build(),
                QuestStage.builder()
                        .title("Джин")
                        .state(State.PLAYING)
                        .id(3)
                        .image("img/LastWish/stage3.png")
                        .text("""
                                Вы решаете открыть бутылку. Как только крышка соскальзывает, перед вами появляется могучее существо — джин.
                                По одному только виду видно, что он разъярен. Он смотрит вокруг, замечает вас и кажется вот-вот будет готов наброситься.""")
                        .selector(Selector.OPTIONS)
                        .option1(new Choice("Объяснить джину, что вы не враг", 4))
                        .option2(new Choice("Сразиться с джином", 301))
                        .button("Выбрать")
                        .build(),
                QuestStage.builder()
                        .title("Поражение!")
                        .state(State.LOST)
                        .id(301)
                        .image("img/LastWish/stage301.png")
                        .text("""
                                Вы решаете сразиться с джином. Геральт, не раз сталкивавшийся с магическими существами, готовится к бою, а Лютик беспокойно наблюдает.
                                Джин издает глухое рычание, и его магическая энергия охватывает все вокруг. Сражение было яростным, но джин оказался слишком могущественным.
                                Он разрушает все вокруг одним ударом своей магии.""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 0))
                        .button("Пройти заново")
                        .build(),
                QuestStage.builder()
                        .title("Желание")
                        .state(State.PLAYING)
                        .id(4)
                        .image("img/LastWish/stage4.png")
                        .text("""
                                Успокоившись, джин смотрит на вас и кажется, начинает понимать, что произошло. Его яростный взгляд смягчается, и вместо угрозы в его голосе звучит любопытство.
                                — Ты... не пытался меня убить, — говорит джин, его голос теперь звучит менее угрожающе, но все еще многозначительно.
                                Ты спас меня, и за это я обязан исполнить одно желание.
                                Он делает паузу, и воздух вокруг начинает пульсировать магией.
                                — У тебя есть одно желание, — продолжает джин, и его взгляд становится пристальным.
                                — Что ты хочешь, смертный?""")
                        .selector(Selector.INPUT)
                        .defaultOption(new Choice("Default Choice", 5))
                        .button("Загадать желание")
                        .build(),
                QuestStage.builder()
                        .title("")
                        .state(State.PLAYING)
                        .id(5)
                        .image("img/LastWish/stage5.png")
                        .text("""
                                Вы произносите свое желание. Джин секунду смотрит на вас, а потом его выражение меняется. Он улыбается — не злой, а скорее искренне заинтересованный.
                                — Ты просишь... — говорит джин, его голос плавно перетекает в звучание грома. — Это необычное желание.
                                — Что ж...
                                Внезапно вокруг вас появляется мощная магическая вуаль и мир вокруг исчезает.""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 6))
                        .button("Далее")
                        .build(),
                QuestStage.builder()
                        .title("Победа!")
                        .state(State.WON)
                        .id(6)
                        .image("img/LastWish/stage6.png")
                        .text("""
                                Очнувшись вы замечаете, что мир вокруг изменился. Необычное спокойствие охватывает ваше сознание.
                                Вокруг больше нет тревог и угроз, как будто все неурядицы исчезли, и наступил настоящий покой.
                                Лютик, стоящий рядом, выглядит немного растерянным, но по его лицу можно прочитать, что он чувствует, как изменился его друг.""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 0))
                        .button("Пройти заново")
                        .build()
        ));
    }
}