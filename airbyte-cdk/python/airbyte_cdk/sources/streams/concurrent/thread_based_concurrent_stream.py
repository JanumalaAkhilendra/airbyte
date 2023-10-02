#
# Copyright (c) 2023 Airbyte, Inc., all rights reserved.
#

from functools import lru_cache
from typing import Any, Iterable, Mapping, Optional

from airbyte_cdk.sources.streams.concurrent.abstract_stream import AbstractStream
from airbyte_cdk.sources.streams.concurrent.availability_strategy import StreamAvailability
from airbyte_cdk.sources.streams.concurrent.partitions.record import Record
from airbyte_protocol.models import AirbyteStream


class ThreadBasedConcurrentStream(AbstractStream):
    def read(self) -> Iterable[Record]:
        raise NotImplementedError

    @property
    def name(self) -> str:
        raise NotImplementedError

    def check_availability(self) -> StreamAvailability:
        raise NotImplementedError

    @property
    def cursor_field(self) -> Optional[str]:
        raise NotImplementedError

    @lru_cache(maxsize=None)
    def get_json_schema(self) -> Mapping[str, Any]:
        raise NotImplementedError

    def get_error_display_message(self, exception: BaseException) -> Optional[str]:
        raise NotImplementedError

    def as_airbyte_stream(self) -> AirbyteStream:
        raise NotImplementedError

    def log_stream_sync_configuration(self) -> None:
        raise NotImplementedError
