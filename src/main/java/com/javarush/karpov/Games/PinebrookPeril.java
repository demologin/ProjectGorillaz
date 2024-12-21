package com.javarush.karpov.Games;

import com.javarush.karpov.entity.*;

import java.util.List;

public class PinebrookPeril extends Quest {

    public PinebrookPeril() {
        super("PinebrookPeril", 2, List.of(
                QuestStage.builder()
                        .title("Пролог")
                        .state(State.PLAYING)
                        .id(0)
                        .image("img/PinebrookPeril/stage0.png")
                        .text("""
                                Ты и твои друзья искатели приключений. Вы бродите по миру в поисках славы, богатства и знаний о древних тайнах.
                                Сегодня вы прибыли в тихую деревушку у подножья Хребта Мира, где решили остановиться проездом, надеясь на короткий отдых. Но покой оказался недолгим.""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 1))
                        .button("Начать приключение")
                        .build(),
                QuestStage.builder()
                        .title("Новое задание")
                        .state(State.PLAYING)
                        .id(1)
                        .image("img/PinebrookPeril/stage1.png")
                        .text("""
                                На входе в деревню вас встречает глава деревенской стражи капитан Эмма-Джейн Коул.
                                — Добро пожаловать в Сосновый Ручей, — сказала она ровным, но настороженным голосом.
                                — Простите за беспокойство, но нам очень нужна ваша помощь. Наши патрули вчера обнаружили странные следы на лесной тропе. Мы боимся, что это могут быть тролли.
                                — Нам нужно проверить территорию, и я надеялась, что вы согласитесь присоединиться к патрулю.""")
                        .selector(Selector.OPTIONS)
                        .option1(new Choice("Согласиться помочь", 2))
                        .option2(new Choice("Отказаться и пойти отдыхать в таверну", 101))
                        .button("Выбрать")
                        .build(),
                QuestStage.builder()
                        .title("Таверна")
                        .state(State.PLAYING)
                        .id(101)
                        .image("img/PinebrookPeril/stage101.png")
                        .text("""
                                Ты и твои друзья всю ночь веселились в таверне, поглощая местное крепкое пиво.
                                Песни, смех и азартные игры продолжались до самого утра.""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 102))
                        .button("Далее")
                        .build(),
                QuestStage.builder()
                        .title("Поражение!")
                        .state(State.LOST)
                        .id(102)
                        .image("img/PinebrookPeril/stage102.png")
                        .text("""
                                Утром вы проснулись с раскалывающейся головой и пустыми кошельками.
                                На столе лежали ваши рюкзаки, перевёрнутые вверх дном.
                                Вы поняли, что потеряли все свое имущество, накопленное за долгие странствия.""")
                        .defaultOption(new Choice("Default Choice", 0))
                        .selector(Selector.NONE)
                        .button("Пройти заново")
                        .build(),
                QuestStage.builder()
                        .title("Дракончик")
                        .state(State.PLAYING)
                        .id(2)
                        .image("img/PinebrookPeril/stage2.png")
                        .text("""
                                Вы соглашаетесь помочь капитану Коул и отправляетесь вместе с ней патрулировать лес.
                                Через некоторое время вы находите в чаще маленького серебряного дракончика, запутавшегося в сетях.
                                Он испуган и ранен.""")
                        .option1(new Choice("Освободить дракончика", 3))
                        .option2(new Choice("Оставить дракончика", 201))
                        .selector(Selector.OPTIONS)
                        .button("Выбрать")
                        .build(),
                QuestStage.builder()
                        .title("Назад в деревню")
                        .state(State.PLAYING)
                        .id(201)
                        .image("img/PinebrookPeril/stage201.png")
                        .text("""
                                Вы оставляете дракончика, решив, что это не ваша проблема.
                                Закончив патруль, вы возвращаетесь в деревню и ложитесь спать.""")
                        .defaultOption(new Choice("Default Choice", 202))
                        .selector(Selector.NONE)
                        .button("Далее")
                                .build(),
                QuestStage.builder()
                        .title("Поражение!")
                        .state(State.LOST)
                        .id(202)
                        .image("img/PinebrookPeril/stage202.png")
                        .text("""
                                На следующий день деревню накрывает густой туман и в воздухе ощущается ледяная магия.
                                В небе появляется разъярённая мать-дракон, ищущая своего малыша.
                                Она разрушает часть деревни и оставляет жителей без крова.""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 0))
                        .button("Пройти заново")
                        .build(),
                QuestStage.builder()
                        .title("Новый друг")
                        .state(State.PLAYING)
                        .id(3)
                        .image("img/PinebrookPeril/stage3.png")
                        .text("""
                                Вы помогаете дракончику выбраться из ловушки. Он дрожит от страха, но не убегает.
                                Вы замечаете, как его золотые глаза с благодарностью смотрят на вас.
                                Он осторожно приближается и касается носом вашей руки, издавая тихое урчание, похожее на звук ветра в горах.
                                — Думаю, он нас не боится, — говорит один из ваших спутников с улыбкой.
                                — Теперь нужно дать ему имя, — предлагает капитан Коул, наблюдая за дракончиком.
                                Вы переглядываетесь. Как вы его назовёте?""")
                        .selector(Selector.INPUT)
                        .defaultOption(new Choice("Default Choice", 4))
                        .button("Придумать имя")
                        .build(),
                QuestStage.builder()
                        .title("В путь")
                        .state(State.PLAYING)
                        .id(4)
                        .image("img/PinebrookPeril/stage4.png")
                        .text("""
                                Капитан Коул достает из рюкзака книгу “Практически полное руководство по драконам” и перелистывает ее на середину.
                                “Похоже это новорожденный серебряный дракон. Логово матери, если верить слухам, находится в ближайшей горе, сразу за лесом.
                                Нам нужно быстро доставить этого детеныша туда. Капитан Коул колеблется, затем вздыхает.
                                “Я должна вернуться в Сосновый Ручей и рассказать о находке. Мне нужно, чтобы вы отнесли малыша в логово его матери.
                                Защитите этого дракона любой ценой.""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 5))
                        .button("Далее")
                        .build(),
                QuestStage.builder()
                        .title("Перед входом")
                        .state(State.PLAYING)
                        .id(5)
                        .image("img/PinebrookPeril/stage5.png")
                        .text("""
                                Следуя по лесным тропам, вы добираетесь до подножия горы.
                                Недалеко впереди вы видите проход в пещеру.
                                Если бы дракон построил логово в горе, очевидно, это был бы вход.""")
                        .selector(Selector.OPTIONS)
                        .option1(new Choice("Осмотреть местность", 6))
                        .option2(new Choice("Войти в пещеру", 501))
                        .button("Выбрать")
                        .build(),
                QuestStage.builder()
                        .title("Поражение!")
                        .state(State.LOST)
                        .id(501)
                        .image("img/PinebrookPeril/stage501.png")
                        .text("""
                                Проявив неосторожность, вы не заметили как рядом, начали дергаться осколки льда.
                                Сосульки и снег собрались вместе, образуя маленьких ледяных существ с длинными острыми когтями.
                                Они напали на вас и ваш отряд был уничтожен.""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 0))
                        .button("Пройти заново")
                        .build(),
                QuestStage.builder()
                        .title("Опасность миновала")
                        .state(State.PLAYING)
                        .id(6)
                        .image("img/PinebrookPeril/stage6.png")
                        .text("""
                                Решив осмотреть местность вы заметили, как рядом притаились Живые сосульки - маленькие ледяные существа, которые часто подстерегают неосторожных путников.
                                Легко с ними разделавшись, вы вошли в пещеру.""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 7))
                        .button("Далее")
                        .build(),
                QuestStage.builder()
                        .title("В логове дракона")
                        .state(State.PLAYING)
                        .id(7)
                        .image("img/PinebrookPeril/stage7.png")
                        .text("""
                                Эта большая пещера заполнена сталактитами и сталагмитами, покрытыми льдом. Потолок поднимается вверх к забитой льдом дыре высоко над вами.
                                Вы замечаете, что на земле в центре комнаты на куче замерзшего снега лежат десятки сундуков с золотом и драгоценными камнями.""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 8))
                        .button("Далее")
                        .build(),
                QuestStage.builder()
                        .title("Что делать")
                        .state(State.PLAYING)
                        .id(8)
                        .image("img/PinebrookPeril/stage8.png")
                        .text("""
                                Внезапно сверху раздается грохот. Вокруг вас падают куски льда и снега, а огромный серебряный дракон прорывается сквозь замерзшую часть потолка пещеры.
                                Существо падает и приземляется перед вами, холодный воздух струится из его пасти.
                                “Что здесь происходит?” - ревет дракон.""")
                        .selector(Selector.OPTIONS)
                        .option1(new Choice("Вы возвращаете дракончика", 9))
                        .option2(new Choice("Вы решаете победить дракона и забрать сокровища", 801))
                        .button("Далее")
                        .build(),
                QuestStage.builder()
                        .title("Поражение")
                        .state(State.LOST)
                        .id(801)
                        .image("img/PinebrookPeril/stage801.png")
                        .text("""
                                Дракон смотрит на вас с разочарованием, а затем в мгновение ока уничтожает вас ледяным дыханием.""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 0))
                        .button("Пройти заново")
                        .build(),
                QuestStage.builder()
                        .title("Победа!")
                        .state(State.WON)
                        .id(9)
                        .image("img/PinebrookPeril/stage9.png")
                        .text("""
                                Дракон благодарен вам за спасение детеныша и дарит вам несколько сундуков с золотом.""")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 0))
                        .button("Пройти заново")
                        .build()
        ));
    }
}