# -*- coding: utf-8 -*-
# Generated by Django 1.10.6 on 2017-03-20 03:17
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='UserInfo',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('username', models.CharField(db_column='user', max_length=32, null=True)),
            ],
        ),
    ]
