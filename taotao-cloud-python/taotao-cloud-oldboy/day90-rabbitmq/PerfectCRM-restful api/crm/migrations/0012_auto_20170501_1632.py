# -*- coding: utf-8 -*-
# Generated by Django 1.10.6 on 2017-05-01 08:32
from __future__ import unicode_literals

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('crm', '0011_auto_20170501_1459'),
    ]

    operations = [
        migrations.AlterModelOptions(
            name='userprofile',
            options={'permissions': (('crm_table_list', '可以查看kingadmin每张表里所有的数据'), ('crm_table_list_view', '可以访问kingadmin表里每条数据的修改页'), ('crm_table_list_change', '可以对kingadmin表里的每条数据进行修改'), ('crm_table_obj_add_view', '可以访问kingadmin每张表的数据增加页'), ('crm_table_obj_add', '可以对kingadmin每张表进行数据添加'))},
        ),
    ]
