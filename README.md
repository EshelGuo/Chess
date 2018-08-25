#ChessView -- 中国象棋

### 使用自定义控件来做的一个象棋游戏, 花了一天时间写了一些基本逻辑(走子 吃子等一般规则), 后续会继续更新

###运行后效果图如下(网上找到图素比较差, 所以看起来有点糊):

###感兴趣的可以下载下来玩一玩

### ChessView 象棋游戏控件使用方式:

**xml中:**

```xml
	
	<com.eshel.chess.chess.game.ChessView
	    android:id="@+id/chess"
	    android:layout_gravity="center"
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"/>
```

**Java代码中:**

```java

	mChessView = findViewById(R.id.chess);
	game = new Game(
			new Rule.Builder()
				.setCamp(Color.RED)//设置阵营, 红方黑方
				.canMoveAllColor(true)//设置黑方是否可动
				.setStyle(new Style(Style.STYLE_SKELETON, Style.STYLE_XQSTUDIO))//设置棋盘棋子样式
				.build());
	mChessView.bindToGame(game);
```

###最后附上Apk下载地址: