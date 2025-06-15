package ru.netology.nmedia

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id=1,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb"
        )

        with(binding) {
            author.text = post.author
            content.text = post.content
            published.text = post.published
            likes.text = getShortSrt(post.likes)
            repost.text = getShortSrt(post.reposts)
            if (post.likeByMe) {
                heart.setImageResource(R.drawable.ic_heart)
            }
            heart.setOnClickListener {
                heart.setImageResource(
                    if (post.likeByMe) {
                        post.likes = post.likes - 1
                        R.drawable.ic_heart
                    } else {
                        post.likes ++
                        R.drawable.ic_red_heart
                    }
                )
                post.likeByMe = !post.likeByMe
                likes.text = getShortSrt(post.likes)
            }
            share.setOnClickListener {
                post.reposts ++
                repost.text = getShortSrt(post.reposts)
            }
        }





//        findViewById<ImageButton>(R.id.heart).setOnClickListener {
//            if (it !is ImageButton) {
//                return@setOnClickListener
//            }
//            (it as ImageButton).setImageResource(R.drawable.ic_red_heart)
//        }
    }

    private fun getShortSrt(n:Int): String {
        when {
            n<1_000 -> return n.toString()
            (n<10_000 && ((n/100) - ((n/1_000)*10))!=0) -> return (n/1_000).toString() + "." + ((n/100) - ((n/1_000)*10)).toString() + "К"
            n<1_000_000 -> return ((n/1_000).toString()+"K")
            (n<10_000_000 && ((n/100_000) - ((n/1_000_000)*10))!=0) -> return (n/1_000_000).toString() + "." + ((n/100_000) - ((n/1_000_000)*10)).toString() + "M"
        }
        return ((n/1_000_000).toString()+"M")
    }

}