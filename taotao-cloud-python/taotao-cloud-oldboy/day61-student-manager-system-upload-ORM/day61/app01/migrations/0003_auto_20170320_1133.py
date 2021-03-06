# -*- coding: utf-8 -*-
# Generated by Django 1.10.6 on 2017-03-20 03:33
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('app01', '0002_auto_20170320_1129'),
    ]

    operations = [
        migrations.AddField(
            model_name='userinfo',
            name='user_type',
            field=models.IntegerField(choices=[(0, '普通用户'), (1, '超级用户'), (2, 'VIP')], default=1),
            preserve_default=False,
        ),
        migrations.AlterField(
            model_name='userinfo',
            name='username',
            field=models.CharField(db_column='user', db_index=True, help_text='...', max_length=32, null=True, verbose_name='用户名'),
        ),
    ]
